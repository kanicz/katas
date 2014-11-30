import org.junit.Test;
import pl.kanicz.DictionaryReplacer;

import java.util.Map;

import static com.google.common.collect.Maps.newLinkedHashMap;
import static org.fest.assertions.Assertions.assertThat;

public class DictionaryReplacerTest {

    public DictionaryReplacer dictionaryReplacer = new DictionaryReplacer();

    @Test
    public void shouldReturnEmptyStringForNullInput() {
        //given
        String input = null;
        Map dict = newLinkedHashMap();

        //when
        String result = dictionaryReplacer.replace(input, dict);

        //then
        assertThat(result).isEqualTo("");
    }

    @Test
    public void shouldReturnEmptyStringForNullDict() {
        //given
        String input = "foo";
        Map dict = null;

        //when
        String result = dictionaryReplacer.replace(input, dict);

        //then
        assertThat(result).isEqualTo("");

    }


    @Test
    public void shouldReturnEmptyStringForEmptyInputAndEmptyDictionary() {
        //given
        String input = "";
        Map dict = newLinkedHashMap();

        //when
        String result = dictionaryReplacer.replace(input, dict);

        //then
        assertThat(result).isEqualTo("");
    }

    @Test
    public void shouldReplaceOneWord() {
        //given
        String input = "$temp$";
        Map<String, String> dict = newLinkedHashMap();
        dict.put("temp", "temporary");

        //when
        String result = dictionaryReplacer.replace(input, dict);

        //then
        assertThat(result).isEqualTo("temporary");
    }

    @Test
    public void shouldReplaceManyWords() {
        //given
        String input = "$temp$ here comes the name $name$";
        Map<String, String> dict = newLinkedHashMap();
        dict.put("temp", "temporary");
        dict.put("name", "John Doe");

        //when
        String result = dictionaryReplacer.replace(input, dict);

        //then
        assertThat(result).isEqualTo("temporary here comes the name John Doe");
    }
}
