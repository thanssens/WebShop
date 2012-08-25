package be.groept.web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * 
 * @author Tom Hanssens
 *
 */
@FacesValidator("stockRangeValidator")
public class StockRangeValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		UIInput stock = (UIInput) component.getAttributes().get("stock"); 
		Integer minStock = Integer.parseInt(stock.getValue().toString()); 
        Integer maxStock = Integer.parseInt(value.toString());

		if (maxStock < minStock) {
			FacesMessage message = new FacesMessage("Stock Till should be equal or larger then Stock From.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

}