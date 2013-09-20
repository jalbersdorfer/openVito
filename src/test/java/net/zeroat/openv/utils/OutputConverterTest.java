package net.zeroat.openv.utils;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OutputConverterTest
{
    @DataProvider(name = "testToLongDataProvider")
    public Object[][] testToLongDataProvider()
    {
        return new Object[][]
        {
                {"1C02", 540l},
                {"1B02", 539l},
                {"1902", 537l},
                {"1802", 536l},
                {"1702", 535l},
                {"0205", 1282l},
                {"f1fb0900", 654321l}
        };
    }

    @Test(dataProvider = "testToLongDataProvider")
    public void testToLong(final String s, final Long expected)
    {
        Long i = OutputConverter.toLong(s);
        Assert.assertEquals(i, expected);
    }
}
