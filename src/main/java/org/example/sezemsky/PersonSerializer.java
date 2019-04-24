package org.example.sezemsky;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author marecek
 */
public class PersonSerializer implements JsonSerializer<Person>  {

    @Override
    public JsonElement serialize(Person p, Type type, JsonSerializationContext jsc) {
        return new JsonPrimitive("1");
    }
    
}
