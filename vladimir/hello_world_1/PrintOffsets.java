import java.lang.Class;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import sun.misc.Unsafe;

public class PrintOffsets {
  public static Unsafe unsafe;

  public static void PrintFields(Class<?> c) {

    Field[] fields = c.getDeclaredFields();
    System.out.printf("============== %s ==============\n", c.getName());

    for (Field f : fields) {
      f.setAccessible(true);
      long offset = 0L;
      if (!Modifier.isStatic(f.getModifiers())) {
        offset = unsafe.objectFieldOffset(f);
      }
      System.out.printf("%s %s : %d\n", f.getType(), f.getName(), offset);
    }

    System.out.printf("\n");
  }

  public static void main(String[] args) throws Exception {
    Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
    unsafeField.setAccessible(true);
    unsafe = (Unsafe) unsafeField.get(null);

    PrintFields(Class.class);
    PrintFields(unsafe.getObject(Class.class, 16L).getClass());
  }
}
