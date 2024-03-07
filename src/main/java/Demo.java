import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.pxl.common.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ArrayList<Double> list1 = new ArrayList<>();
        list1.add(0.0);list1.add(0.0);list1.add(1.0);list1.add(1.0);list1.add(0.0);
        ArrayList<Double> list2 = new ArrayList<>();
        list2.add(220.0);list2.add(200.0);list2.add(20.0);list2.add(10.0);list2.add(0.0);

        double v = cosineSimilarity(list1, list2);
        System.out.println(v);
    }


    public static double cosineSimilarity(List<Double> vectorA, List<Double> vectorB) {
        double dotProduct = 0.0;
        for (int i = 0; i < vectorA.size(); i++) {
            dotProduct += vectorA.get(i) * vectorB.get(i);
        }
        return dotProduct;
    }


}

