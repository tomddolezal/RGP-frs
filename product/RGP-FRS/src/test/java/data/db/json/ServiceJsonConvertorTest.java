package data.db.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.db.Service;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class ServiceJsonConvertorTest {
    //language=json
    private final static String SERVICE_JSON = "{\n" +
            "          \"type\": \"GERIATRIC OUTPATIENT CLINICS\",\n" +
            "          \"service\": \"Wellness for Independent Seniors (WISE) Outpatient Clinic\",\n" +
            "          \"UUID\": \"1cce14c3-b649-45f8-845f-59d72d96675f\",\n" +
            "          \"contact\": [\"T: 416-323-6400 Ext. 8092\" , \"F: 416-323-7324\"],\n" +
            "          \"waittime\":\"\"\n" +
            "        }";

    @Test
    public void shouldDeserializeSerializeAndDeserializeAndStillBeSameObject() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Service.class, new ServiceJsonConvertor());
        Gson gson = builder.create();

        Service service = gson.fromJson(SERVICE_JSON, Service.class);

        assertThat(service, is(gson.fromJson(gson.toJson(service), Service.class)));
    }
}
