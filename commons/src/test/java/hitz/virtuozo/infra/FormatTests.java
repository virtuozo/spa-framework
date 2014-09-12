package hitz.virtuozo.infra;

import hitz.virtuozo.infra.CEPFormat;
import hitz.virtuozo.infra.CNPJFormat;
import hitz.virtuozo.infra.CPFFormat;
import hitz.virtuozo.infra.HexaFormat;
import junit.framework.Assert;

import org.junit.Test;

public class FormatTests {

    @Test
    public void testShouldFormatCEPString() {
	String rawValue = "01234567";
	String cepValue = CEPFormat.get().format(rawValue);

	Assert.assertEquals("01234-567", cepValue);
	Assert.assertSame(null, CEPFormat.get().format(null));
    }

    @Test
    public void testShouldUnformatCEPString() {
	String cepValue = "01234-567";
	String rawValue = CEPFormat.get().unformat(cepValue);

	Assert.assertEquals("01234567", rawValue);
	Assert.assertSame(null, CEPFormat.get().unformat(null));
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testShouldFailOnInvalidCEPString() {
	String cepValue = "0123";
	CEPFormat.get().format(cepValue);
    }

    @Test
    public void testShouldFormatCNPJFormat() {
	String rawValue = "12345678000199";
	String cnpjValue = CNPJFormat.get().format(rawValue);

	Assert.assertEquals("12.345.678/0001-99", cnpjValue);
	Assert.assertSame(null, CNPJFormat.get().format(null));
    }

    @Test
    public void testShouldUnformatCNPJFormat() {
	String cnpjValue = "12.345.678/0001-99";
	String rawValue = CNPJFormat.get().unformat(cnpjValue);

	Assert.assertEquals("12345678000199", rawValue);
	Assert.assertSame(null, CNPJFormat.get().unformat(null));
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testShouldFailOnInvalidCNPJString() {
	String cepValue = "12345678910";
	CNPJFormat.get().format(cepValue);
    }

    @Test
    public void testShouldFormatCPFFormat() {
	String rawValue = "12345678910";
	String cnpjValue = CPFFormat.get().format(rawValue);

	Assert.assertEquals("123.456.789-10", cnpjValue);
	Assert.assertSame(null, CPFFormat.get().format(null));
    }

    @Test
    public void testShouldUnformatCPFFormat() {
	String cnpjValue = "123.456.789-10";
	String rawValue = CPFFormat.get().unformat(cnpjValue);

	Assert.assertEquals("12345678910", rawValue);
	Assert.assertSame(null, CPFFormat.get().unformat(null));
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testShouldFailOnInvalidCPFString() {
	String cepValue = "12345678";
	CPFFormat.get().format(cepValue);
    }

    @Test
    public void testShouldFormatHexaFormat() {
	byte[] rawValue = "Brazoft".getBytes();
	String hexaValue = HexaFormat.get().format(rawValue);

	Assert.assertEquals("4272617a6f6674", hexaValue);
	Assert.assertSame(null, HexaFormat.get().format(null));
    }

    @Test
    public void testShouldUnformatHexaFormat() {
	String hexaValue = "4272617a6f6674";
	byte[] rawValue = HexaFormat.get().unformat(hexaValue);

	Assert.assertEquals("Brazoft", new String(rawValue));
	Assert.assertSame(null, HexaFormat.get().unformat(null));
    }
}