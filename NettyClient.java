import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
//import jdk.nashorn.internal.runtime.linker.Bootstrap;

public class NettyClient {
    public static void main(String[] args){
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                //指定线程模型
                .group(workerGroup)
                //指定io交互模型
                .channel(NioSocketChannel.class)
                //io处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch){

                    }
                });
        connect(bootstrap);
    }

    private static void connect(Bootstrap bootstrap){
        //连接远程服务器
        bootstrap.connect("127.0.0.1",8000).addListener(future ->{
            if(future.isSuccess()){
                System.out.println("连接成功");
            }else {
                System.out.println("连接失败");
                Thread.sleep(2000);
                connect(bootstrap);
            }
        });
    }
}
