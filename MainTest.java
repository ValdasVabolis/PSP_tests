package com.valdas.test;

import com.valdas.EmailValidator;
import com.valdas.PasswordChecker;
import com.valdas.PhoneValidator;
import org.junit.*;

import static org.junit.Assert.*;

public class MainTest {

    private PasswordChecker passwordChecker;
    private PhoneValidator phoneValidator;
    private EmailValidator emailValidator;

    @Before
    public void setUp() {
        passwordChecker = new PasswordChecker();
        phoneValidator = new PhoneValidator();
        emailValidator = new EmailValidator();
    }

    /* PasswordChecker tests */

    @Test
    public void test_password_length_not_shorter_than_x() {
        String password = "tespas";
        int minLength = 6;
        assertTrue(passwordChecker.containsMinSymbols(password, minLength));
    }

    @Test
    public void test_password_contains_uppercase() {
        String password = "TestPas";
        assertTrue(passwordChecker.containsUppercase(password));
    }

    @Test
    public void test_password_contains_special_symbols() {
        String specialSymbols = "test#$%^&*()!";
        assertTrue(passwordChecker.containsSpecialSymbols(specialSymbols));
    }

    /* PhoneValidator tests */

    @Test
    public void test_phone_invalid_symbols() {
        String phoneNumber = "#860000000";
        assertTrue(phoneValidator.containsInvalidSymbols(phoneNumber));
    }

    @Test
    public void test_phone_number_convert() {
        String phoneNumber = "867400000";
        assertEquals(phoneValidator.convertPhoneNumber(phoneNumber),"+37067400000");
    }

    @Test
    public void test_phone_correct_prefix() {
        String phoneNumber = "+37067400000";
        String prefix = "LT";
        int prefixLength = 3;
        assertEquals(phoneValidator.containsCorrectPrefix(prefix, prefixLength, phoneNumber));
    }

    /* EmailValidator test */

    @Test
    public void test_email_has_at_symbol() {
        String email = "test@test.com";
        assertTrue(emailValidator.containsAtSymbol(email));
    }

    @Test
    public void test_email_has_no_whitespace() {
        String email = " @test.lt";
        assertFalse(emailValidator.isValidSymbols(email));
    }

    @Test
    public void test_email_has_no_invalid_symbol() {
        String email = "john..doe@example.com";
        assertFalse(emailValidator.isValidSymbols(email));
        email = "john.doe@example..com";
        assertFalse(emailValidator.isValidSymbols(email));
        email = "a\"b(c)d,e:f;gi[j\\k]l@example.com";
        assertFalse(emailValidator.isValidSymbols(email));

    }
    @Test
    public void test_email_contains_valid_domain_and_tld() {
        String email = "test@test.lt";
        assertTrue(emailValidator.isValidDomain(email));
    }

}
