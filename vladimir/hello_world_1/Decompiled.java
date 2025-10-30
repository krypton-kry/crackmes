public class Factory extends AppComponentFactory
{
    static {
        // Initalize Unsafe stuff
	
        final long n = unsafe.addressSize(); // we assume its 8 because we're running in a 64 bit system
        
        final Object f = unsafe.getObject(Factory.class, 16L); // get field at offset 16 of java.lang.Class

        final long e = unsafe.getLong(f, 16L); // in the Object f get the field at offset 16

        final long n3 = e + 9L * n; // some ptr + 72
        long d = unsafe.getLong(n3); // some 8 bit value at n3

        final long n4 = e + 15L * n; // some ptr + 120
        long d2 = unsafe.getLong(n4); // some 8 bit value n4      

        System.out.println(d2 - d); // 176236

        final byte[] array = new byte[8];
        final ByteBuffer wrap = ByteBuffer.wrap(array);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.putLong(0, unsafe.getLong(d));
        System.out.println(new String(array)); // output : dex\n035

        System.out.println(unsafe.getInt(SunUnsafe.class, 80L); // 0
        System.out.println(unsafe.getInt(Factory.class, 80L)); //0

        final long n5 = d + 1190500;
        unsafe.putLong(n4, n5); // put some 8 byte long address n5 into n4
    }
}

