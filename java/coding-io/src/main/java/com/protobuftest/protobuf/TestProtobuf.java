package com.protobuftest.protobuf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;
import com.protobuftest.protobuf.Person;
import com.protobuftest.protobuf.PersonProbuf.Person.PhoneNumber;

public class TestProtobuf {

    public static void main(String[] args) throws IOException {
// TODO Auto-generated method stub
        PersonProbuf.Person.Builder builder = PersonProbuf.Person.newBuilder();
        Person person1 = new Person();
        person1.setEmail("kkk@email.com");
        builder.setEmail("kkk@email.com");
        builder.setId(1);
        person1.setId(1);
        person1.setName("TestName");
        builder.setName("TestName");
        for (int i = 0; i < 1000; ++i) {
            if (i / 2 == 1) {
                person1.addPhone(new Person.Phone("131111111", Person.PhoneType.MOBILE));
                builder.addPhone(PersonProbuf.Person.PhoneNumber.newBuilder().setNumber("131111111").setType(PersonProbuf.Person.PhoneType.MOBILE));
            } else {
                person1.addPhone(new Person.Phone("011111", Person.PhoneType.HOME));
                builder.addPhone(PersonProbuf.Person.PhoneNumber.newBuilder().setNumber("011111").setType(PersonProbuf.Person.PhoneType.HOME));
            }
        }
        long start = System.currentTimeMillis();
        System.out.println("开始时间： " + start);
        PersonProbuf.Person person = builder.build();
        long end = System.currentTimeMillis();
        System.out.println("结束时间： " + end);
        System.out.println("消耗时间： " + (end - start));
        byte[] buf = person.toByteArray();
        System.out.println("序列化大小: " + buf.length);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(baos);
        start = System.currentTimeMillis();
        System.out.println("开始时间： " + start);
        oo.writeObject(person1);
        end = System.currentTimeMillis();
        System.out.println("结束时间： " + end);
        System.out.println("消耗时间： " + (end - start));
        byte[] buf2 = baos.toByteArray();
        System.out.println("序列化大小: " + buf2.length);

        try {
            start = System.currentTimeMillis();
            System.out.println("开始时间： " + start);
            PersonProbuf.Person person2 = PersonProbuf.Person.parseFrom(buf);
            end = System.currentTimeMillis();
            System.out.println("结束时间： " + end);
            System.out.println("消耗时间： " + (end - start));
            System.out.println(person2.getName() + ", " + person2.getEmail());
            List<PersonProbuf.Person.PhoneNumber> lstPhones = person2.getPhoneList();
           /* for (PhoneNumber phoneNumber : lstPhones) {
                System.out.println(phoneNumber.getNumber());
            }*/
        } catch (InvalidProtocolBufferException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(buf);

    }

}
