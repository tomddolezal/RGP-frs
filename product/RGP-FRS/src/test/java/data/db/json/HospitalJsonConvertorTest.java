package data.db.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.db.Hospital;
import data.db.Service;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HospitalJsonConvertorTest {

    //language=json
    private final static String HOSPITAL_JSON = "   {\n" +
            "      \"hospital\":\"Women's College Hospital\",\n" +
            "      \"UUID\": \"c04a3d7f-d640-4916-9deb-6325cdc613ef\",\n" +
            "      \"address\": \"76 Grenville St, Toronto, ON M5S 1B2, Canada\",\n" +
            "      \"website\": \"\",\n" +
            "      \"services\":[\n" +
            "        {\n" +
            "          \"type\": \"GERIATRIC OUTPATIENT CLINICS\",\n" +
            "          \"service\": \"Wellness for Independent Seniors (WISE) Outpatient Clinic\",\n" +
            "          \"UUID\": \"1cce14c3-b649-45f8-845f-59d72d96675f\",\n" +
            "          \"contact\": [\"T: 416-323-6400 Ext. 8092\" , \"F: 416-323-7324\"],\n" +
            "          \"waittime\":\"\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }";

    @Test
    public void shouldDeserializeSerializeCorrectlyPersistingData() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Hospital.class, new HospitalJsonConvertor());
        builder.registerTypeAdapter(Service.class, new ServiceJsonConvertor());
        Gson gson = builder.create();

        Hospital hospital = gson.fromJson(HOSPITAL_JSON, Hospital.class);

        assertThat(hospital, is(gson.fromJson(gson.toJson(hospital), Hospital.class)));
    }
}
