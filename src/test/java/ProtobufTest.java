import com.google.protobuf.InvalidProtocolBufferException;
import com.netty.domain.proto.AddressBook;
import com.netty.domain.proto.AddressBook.Person;
import org.junit.Test;

/**
 * Created by wangyong on 2017/7/6.
 */
public class ProtobufTest {


  @Test
  public void protobufTest() throws InvalidProtocolBufferException {

    //构建person对象
    Person person = AddressBook.Person.newBuilder()
        .setId(1)
        .setEmail("admin@126.com")
        .setName("admin")
        .build();

    System.out.println(person);
    //序列化成byte数组
    byte[] personByteArray = person.toByteArray();
    //反序列化成person对象
    Person personFromByte = Person.parseFrom(personByteArray);

    System.out.println(personFromByte);
  }
}
