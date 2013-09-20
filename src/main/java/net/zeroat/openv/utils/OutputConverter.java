package net.zeroat.openv.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * <b>Title:</b> OutputConverter <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2013 <br>
 * <b>Company:</b> Speed4Trade GmbH <br>
 *
 * @author ja
 * @version 1.0.0
 */
public final class OutputConverter
{
    private OutputConverter()
    {
        super();
    }

    /**
     * To decode the given Little Endian Byte String to a Long Value.
     *
     * @param s the Byte String to decode.
     * @return the corresponding Value as long or 0 on error.
     */
    public static Long toLong(final String s)
    {
        byte[] b = {0, 0};
        try
        {
            b = Hex.decodeHex(s.toCharArray());
            switch (b.length)
            {
            case 2:
                return Long.valueOf(ByteBuffer.wrap(b).order(ByteOrder.LITTLE_ENDIAN).getShort());
            case 4:
                return Long.valueOf(ByteBuffer.wrap(b).order(ByteOrder.LITTLE_ENDIAN).getInt());
            case 8:
                return ByteBuffer.wrap(b).order(ByteOrder.LITTLE_ENDIAN).getLong();
            default:
                break;
            }
        }
        catch (DecoderException e)
        {
        }

        return 0L;
    }
}
