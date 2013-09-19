package net.zeroat.openv.handler;

public interface VitoClient
{
    /**
     * Gets the normal target Temperature for Circle M1 in Grad Celsius.
     *
     * @return the Temperature.
     */
    public Integer getTemperatureM1();

    /**
     * Sets the normal target Temperature for Circle M1.
     *
     * @param value the Temperature in Grad Celsius.
     */
    public void setTemperatureM1(final Integer value);
}
