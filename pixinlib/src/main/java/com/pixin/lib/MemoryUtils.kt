package PackagePixinLib

class MemoryUtils {

    fun getVMCacheSize(): Int {
        //Getting Maximum Device Cache Size
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        return maxMemory
    }

}