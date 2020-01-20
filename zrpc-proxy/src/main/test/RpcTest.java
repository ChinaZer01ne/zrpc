import com.github.zrpc.container.mock.ConsumerService;
import com.github.zrpc.proxy.factory.JavaProxyFactory;
import com.github.zrpc.proxy.factory.ProxyFactory;

public class RpcTest {

    public static void main(String[] args) {
        ProxyFactory proxy = new JavaProxyFactory();
        ProducerService serviceProxy = (ProducerService) proxy.createProxy(new Class[]{ProducerService.class});
        Integer sum = serviceProxy.add(1, 2);
        System.out.println(sum);
    }
}