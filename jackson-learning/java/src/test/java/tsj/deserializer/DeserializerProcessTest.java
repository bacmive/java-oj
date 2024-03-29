package tsj.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class DeserializerProcessTest {

    @Test
    public void fun1() throws JsonProcessingException {
        JsonMapper jsonMapper = new JsonMapper();

        String jsonStr = "{\"name\":\"YourBatman\",\"age\":18, \"birthDay\": \"2020-01-17\"}";
        Person person = jsonMapper.readValue(jsonStr, Person.class);
        System.out.println(person);
    }

    @Test
    public void fun2() throws JsonProcessingException {
        JsonMapper jsonMapper = new JsonMapper();

        String jsonStr = "[\"a\",\"b\",\"c\"]";
        String[] stringArr = jsonMapper.readValue(jsonStr, String[].class);
        System.out.println(Arrays.toString(stringArr));
    }


    @Test
    public void fun3() throws JsonProcessingException {
        JsonMapper jsonMapper = new JsonMapper();

        String jsonStr = "{\"name\":\"YourBatman\",\"age\":18}";
        Map<String, Object> map = jsonMapper.readValue(jsonStr, Map.class);
        System.out.println(map.getClass());
        System.out.println(map);
    }


    @Test
    public void fun4() throws JsonProcessingException {
        JsonMapper jsonMapper = new JsonMapper();

        String jsonStr = "[\"a\",\"b\",\"c\"]";

        // 注意：若不写泛型，使用是更加通用的CollectionDeserializer
        // List list = jsonMapper.readValue(jsonStr, List.class);

        // 若写了泛型，使用的便是精确的StringCollectionDeserializer
        List list = jsonMapper.readValue(jsonStr, new TypeReference<List<String>>() {
        });
        System.out.println(list.getClass());
        System.out.println(list);
    }


    @Test
    public void fun5() throws JsonProcessingException {
        JsonMapper jsonMapper = new JsonMapper();

        String jsonStr = "[\"1\",\"2\",\"3\"]";

        int[] intArr = jsonMapper.readValue(jsonStr, int[].class);
        System.out.println(Arrays.toString(intArr));
    }


    @Test
    public void fun6() throws JsonProcessingException {
        JsonMapper jsonMapper = new JsonMapper();
        //https://blog.csdn.net/u013614451/article/details/39699007
        // DeserializationConfig cfg = jsonMapper.getDeserializationConfig();
        // SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // cfg.setDateFormate(myDateFormat);

        //ref:https://www.cnblogs.com/z-qinfeng/p/13341359.html
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        jsonMapper.registerModule(javaTimeModule);
        String jsonStr = "\"2021-11-27\"";
        // String jsonStr = "{\"year\":2020,\"month\":\"JANUARY\", \"dayOfMonth\": 17}";
        LocalDate localDate = jsonMapper.readValue(jsonStr, LocalDate.class);
        System.out.println(localDate);
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    private static class Person {

        // @JsonValue // "YourBatman"
        private String name = "YourBatman";
        private Integer age = 18;
        private Date birthDay;
        private Long birthDayLong; // 很多人喜欢用Long表示时间
        private String remark; // 特意让其为null
    }
}
