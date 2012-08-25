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
@FacesValidator("priceRangeValidator")
public class PriceRangeValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		UIInput price = (UIInput) component.getAttributes().get("price"); 
		Integer minPrice = Integer.parseInt(price.getValue().toString()); 
        Integer maxPrice = Integer.parseInt(value.toString());

		if (maxPrice < minPrice) {
			FacesMessage message = new FacesMessage("Product Price Till should be equal or larger then Product Price From.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

}