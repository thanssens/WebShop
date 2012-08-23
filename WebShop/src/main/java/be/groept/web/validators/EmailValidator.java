package be.groept.web.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * 
 * @author Tom Hanssens
 *
 */
@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	private static final String regex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern = Pattern.compile(regex);
	private Matcher matcher;

	@Override
	public void validate(FacesContext context, UIComponent component, Object email) throws ValidatorException {
		matcher = pattern.matcher(email.toString());

		if (((email != "") || (email != null)) && (!matcher.matches())) {
			FacesMessage message = new FacesMessage("Email should be of a correct format.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

}