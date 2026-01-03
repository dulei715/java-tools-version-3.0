import org.apache.poi.util.StringUtil;
import org.junit.Test;

public class OtherTest {
    @Test
    public void fun1() {
        String[] strArr = {"haha", "xixi", "hehe"};
        String join = StringUtil.join(strArr, "; ");
        System.out.println(join);
    }
}
