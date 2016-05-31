package utils;

public class FieldsValidator {
/*

    public static void showValidationMessage(String message, EditText editText, Context context) {

        if(editText != null) {
            editText.setError(message);
            editText.requestFocus();
        }
    }

    public static boolean validateEmail(EditText editText) {

        Context context = editText.getContext();

        String emptyField = context.getString(R.string.empty_email);
        String invalidField = context.getString(R.string.invalid_username);

        String text = editText.getText().toString().trim();

        if(StringUtils.isEmpty(text)) {

            showValidationMessage(emptyField, editText, context);

            return false;

        } else if(!isValidEmail(text)) {

            showValidationMessage(invalidField, editText, context);

            return false;
        }

        return true;
    }

    public static boolean validatePassword(EditText editText) {

        Context context = editText.getContext();

        String emptyField = context.getString(R.string.empty_password);

        String text = editText.getText().toString().trim();

        if(StringUtils.isEmpty(text)) {

            showValidationMessage(emptyField, editText, context);

            return false;

        }

        return true;
    }

    public static boolean matchPasswords(EditText editText1, EditText editText2) {

        Context context = editText1.getContext();

        String mismatch = context.getString(R.string.password_number_not_matched);

        String text = editText1.getText().toString().trim();
        String text2 = editText2.getText().toString().trim();

        if(!StringUtils.isEmpty(text)) {

            boolean matched = text.equals(text2);

            if(!matched) {

                showValidationMessage(mismatch, editText2, context);
            }

            return matched;
        }

        return false;
    }

    public static boolean validateIFSC(EditText editText) {

        Context context = editText.getContext();

        String emptyField = context.getString(R.string.empty_ifsc);
        String invalidField = context.getString(R.string.invalid_ifsc);

        String text = editText.getText().toString().trim();

        if(StringUtils.isEmpty(text)) {

            showValidationMessage(emptyField, editText, context);

            return false;

        }

        return true;
    }

    public static boolean validateBankAccNumber(EditText editText) {

        Context context = editText.getContext();

        String emptyField = context.getString(R.string.empty_account_number);
        String invalidField = context.getString(R.string.invalid_account_number);

        String text = editText.getText().toString().trim();

        if(StringUtils.isEmpty(text)) {

            showValidationMessage(emptyField, editText, context);

            return false;

        }

        return true;
    }

    public static boolean validatePersonName(EditText editText) {

        Context context = editText.getContext();

        String emptyField = context.getString(R.string.empty_name);

        String text = editText.getText().toString().trim();

        if(StringUtils.isEmpty(text)) {

            showValidationMessage(emptyField, editText, context);

            return false;

        }

        return true;
    }

    public static boolean validateAmount(EditText editText) {

        Context context = editText.getContext();

        String emptyField = context.getString(R.string.empty_amount);

        String text = editText.getText().toString().trim();

        if(StringUtils.isEmpty(text)) {

            showValidationMessage(emptyField, editText, context);

            return false;

        }

        return true;
    }

    public static boolean validatePIN(EditText editText) {

        Context context = editText.getContext();

        String emptyField = context.getString(R.string.validate_pin_length);

        String text = editText.getText().toString().trim();

        if(StringUtils.isEmpty(text) || text.length() <= 0) {

            showValidationMessage(emptyField, editText, context);

            return false;

        }

        return true;
    }

    public static boolean matchPINs(EditText editText1, EditText editText2) {

        Context context = editText1.getContext();

        String mismatch = context.getString(R.string.validate_confirm_pin_mismatch);

        String text = editText1.getText().toString().trim();
        String text2 = editText2.getText().toString().trim();

        if(!StringUtils.isEmpty(text)) {

            boolean matched = text.equals(text2);

            if(!matched) {

                showValidationMessage(mismatch, editText2, context);
            }

            return matched;
        }

        return false;
    }



    public static boolean validateEventName(EditText editText) {

        Context context = editText.getContext();

        String emptyField = context.getString(R.string.empty_event_name);

        String text = editText.getText().toString().trim();

        if(StringUtils.isEmpty(text)) {

            showValidationMessage(emptyField, editText, context);

            return false;

        }

        return true;
    }


    public static boolean matchAccountNumbers(EditText editText1, EditText editText2) {

        Context context = editText1.getContext();

        String mismatch = context.getString(R.string.account_number_not_matched);

        String text = editText1.getText().toString().trim();
        String text2 = editText2.getText().toString().trim();

        if(!StringUtils.isEmpty(text)) {

            boolean matched = text.equals(text2);

            if(!matched) {

                showValidationMessage(mismatch, editText2, context);
            }

            return matched;
        }

        return false;
    }

    public static boolean validatePaymentAddress(EditText editText, boolean supressMsg) {

        Context context = editText.getContext();

        String emptyField = context.getString(R.string.validation_payemt_address_empty);
        String invalidField = context.getString(R.string.validation_payemt_address_invalid);

        String text = editText.getText().toString().trim();

        if(StringUtils.isEmpty(text)) {

            if(!supressMsg) {

                showValidationMessage(emptyField, editText, context);
            }

            return false;

        } else if(text.length() < 4 || text.contains("..") || text.startsWith(".") || text.startsWith("-") || text.endsWith(".") || text.endsWith("-") || text.contains("--") || text.contains(".-") || text.contains("-.")) {

            if(!supressMsg) {

                showValidationMessage(invalidField, editText, context);
            }

            return false;
        }

        return true;
    }


    public static boolean isValidPhoneNumber(CharSequence target) {

        if(target == null || StringUtils.isEmpty(target)) {

            return false;

        } else {

            return Patterns.PHONE.matcher(target).matches();
        }
    }

    public static boolean isValidEmail(CharSequence target) {

        if(target == null || StringUtils.isEmpty(target)) {

            return false;

        } else {

            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }*/

}
