package com.github.farafonoff.deskpetsir.control;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Artem_Farafonov on 12/22/2015.
 */
public class Action implements Parcelable {
    static Action STOP = new Action("STOP", "3537    1129     710     471    1236     551\n" +
            "             1346     434    1236     546    1228    1143\n" +
            "              743    1031     647    1122     662     542\n" +
            "             1256    1111     632    1134     764    1048\n" +
            "              637    1132     774     422    1321     446\n" +
            "             1238     541    1244");
    static List<Action> actionList = Arrays.asList(
            new Action("LEFTFORWARD", "3630    1021     731     450    1338     448\n" +
                    "             1327    1042     760     445    1314    1022\n" +
                    "              746    1036     753    1044     736     463\n" +
                    "             1403     950     754    1015     744     454\n" +
                    "             1337    1025     759     452    1327     442\n" +
                    "             1334     459    1354"),
            new Action("FORWARD", "3629     998     793     401    1287    1078\n" +
                    "              759     436    1401     385    1373     401\n" +
                    "             1346    1020     813     984     782     398\n" +
                    "             1377    1010     780     386    1343    1016\n" +
                    "              686    1093     767    1013     793     410\n" +
                    "             1346     436    1338"),
            new Action("RIGHTFORWARD", "3523    1029     739     458    1352    1010\n" +
                    "              747     463    1355    1052     704     445\n" +
                    "             1363    1003     772     997     755     443\n" +
                    "             1371     986     712     494    1337    1034\n" +
                    "              739     454    1317    1026     660     542\n" +
                    "             1328     460    1393"),
            new Action("LEFTROLL", "3622    1003     672    1109     766    1008\n" +
                    "              776     442    1363     414    1316     464\n" +
                    "             1349    1029     759    1009     794     396\n" +
                    "             1354     437    1223     540    1352    1001\n" +
                    "              773    1039     773     990     761     427\n" +
                    "             1347     433    1314"),
            STOP,
            new Action("RIGHTROLL", "3632    1004     756     449    1245     538\n" +
                    "             1369     408    1357     416    1349    1022\n" +
                    "              787     405    1346    1009     670     544\n" +
                    "             1352     994     659    1156     715    1034\n" +
                    "              777    1012     761     427    1349    1027\n" +
                    "              759     425    1327"),
            new Action("LEFTBACK", "3570    1062     634     599    1173     574\n" +
                    "             1289    1061     752    1034     701     539\n" +
                    "             1175    1141     766    1010     724     490\n" +
                    "             1314    1046     671    1101     675     556\n" +
                    "             1172     576    1287    1070     707     487\n" +
                    "             1289     485    1205"),
            new Action("BACKWARD", "3545     531    1239    1125     726     460\n" +
                    "             1339     440    1382     399    1260    1086\n" +
                    "              662     559    1233    1113     763    1024\n" +
                    "              795     398    1282    1069     699    1083\n" +
                    "              751    1035     728     463    1314    1053\n" +
                    "              733     464    1324"),
            new Action("RIGHTBACK", "3540    1067     645    1154     729    1046\n" +
                    "              675     555    1175    1144     704     537\n" +
                    "             1171    1163     639    1120     671     556\n" +
                    "             1174     590    1226     546    1288    1062\n" +
                    "              639     598    1174    1158     704     512\n" +
                    "             1174     581    1253"),
            new Action("Z", "3624    1004     781     424    1350    1010\n" +
                    "              744    1037     767     429    1366     424\n" +
                    "             1258     514    1359    1008     782     412\n" +
                    "             1391     983     657     527    1314     460\n" +
                    "             1352    1008     786    1027     765    1000\n" +
                    "              758     426    1347"),
            new Action("X", "3657    1000     795     968     690    1089\n" +
                    "              775     424    1291     507    1240    1111\n" +
                    "              771    1009     776     424    1352     425\n" +
                    "             1352     422    1239     548    1250    1109\n" +
                    "              768    1003     776     442    1349     420\n" +
                    "             1343    1005     680"),
            new Action("C", "3594    1014     775    1010     817     970\n" +
                    "              764     420    1354    1018     689    1080\n" +
                    "              759     429    1251    1113     767     428\n" +
                    "             1346     487    1253     476    1358    1010\n" +
                    "              656     540    1357     430    1343    1012\n" +
                    "              761     444    1346")
    );

    public Action(String name, String command) {
        this.name = name;
        this.command = command;
    }

    public String name;
    public String command;

    protected Action(Parcel in) {
        name = in.readString();
        command = in.readString();
    }

    public static final Creator<Action> CREATOR = new Creator<Action>() {
        @Override
        public Action createFromParcel(Parcel in) {
            return new Action(in);
        }

        @Override
        public Action[] newArray(int size) {
            return new Action[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(command);
    }
}
