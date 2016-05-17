import com.google.protobuf.ExtensionRegistry;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created: 2016/5/13.
 * Author: Qiannan Lu
 */
public class TestAnimalExt {
    public static void main(String[] args) throws IOException {
        DogProto.Dog.Builder builder = DogProto.Dog.newBuilder();
        builder.setBonesBuried(1);



        AnimalProto.Animal.Builder animal = AnimalProto.Animal.newBuilder();
        animal.setType(AnimalProto.Animal.Type.Dog);
        animal.setExtension(DogProto.Dog.animal, builder.build());
        animal.build();


        ByteArrayOutputStream output = new ByteArrayOutputStream();
        animal.build().writeTo(output);
        byte[] byteArray = output.toByteArray();
        ByteArrayInputStream input = new ByteArrayInputStream(byteArray);

        // -------------- 分割线：下面是接收方，将数据接收后反序列化 ---------------

        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(DogProto.Dog.animal); // 或者Example.registerAllExtensions(registry);
        AnimalProto.Animal receiveBaseData = AnimalProto.Animal.parseFrom(input, registry);
        System.out.println(receiveBaseData.getType());
        System.out.println(receiveBaseData.getExtension(DogProto.Dog.animal).getBonesBuried());

//        animal.setExtension(DogProto.Dog.ANIMAL_FIELD_NUMBER, builder.build());
    }
}
