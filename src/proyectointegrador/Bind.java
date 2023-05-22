package proyectointegrador;

import java.io.*;
import java.util.*;
import javax.json.*;
import javax.json.stream.*;

public class Bind {

    public Map<String, String> Reader() {
        Map<String, String> dict = new HashMap<>();
        List<String> tls = new ArrayList<>();
        List<String> key = new ArrayList<>();
        JsonReader reader = null;
        try {
            reader = Json.createReader(new FileReader("bind.json"));
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        JsonStructure jst = reader.read();
        StringWriter swr = new StringWriter();
        try (JsonWriter jwr = Json.createWriter(swr)) {
            jwr.writeObject((JsonObject) jst);
        } catch (Exception e) {
            System.out.println(e);
        }
        String jsd = swr.toString();
        JsonParser parser = Json.createParser(new StringReader(jsd));
        while (parser.hasNext()) {
            JsonParser.Event evt = parser.next();
            switch (evt) {
                case KEY_NAME -> {
                    key.add(parser.getString());
                }
                case VALUE_STRING -> {
                    tls.add(parser.getString());
                }
            }
        }
        int idx = 0;
        for (String k : key) {
            dict.put(k, tls.get(idx));
            idx++;
        }
        return dict;
    }

    public void Setter(String tp, String fn) {
        FileWriter wr = null;
        try {
            wr = new FileWriter("bind.json");
        } catch (IOException e) {
            System.out.println(e);
        }
        try (JsonGenerator jsg = Json.createGenerator(wr)) {
            jsg.writeStartObject()
                    .write("type", tp)
                    .write("eval", fn)
                    .write("result", "")
                    .write("error", "")
                    .writeEnd()
                    .close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
