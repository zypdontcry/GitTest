package junit;

import org.junit.Test;
import util.ValidateCodeUtil;

public class JunitTest 
{
    @Test
    public void test() 
    {
        String code = ValidateCodeUtil.productCode();
        System.out.println(code);
    }
    
    @Test
    public void test2() 
    {
       
    }

}
