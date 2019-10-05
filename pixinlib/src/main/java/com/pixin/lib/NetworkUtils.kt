package PackagePixinLib;

import android.content.Context
import android.net.ConnectivityManager
import java.sql.DriverManager.println

class NetworkUtils {

    companion object {

        fun verifyAvailableNetwork(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }


}