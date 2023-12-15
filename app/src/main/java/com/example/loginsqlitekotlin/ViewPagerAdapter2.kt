import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.loginsqlitekotlin.fragment.tomas.TomasInfo
import com.example.loginsqlitekotlin.fragment.tomas.TomasTransaction



class ViewPagerAdapter2(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TomasInfo()
            1 -> TomasTransaction()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Informasi"
            1 -> "Transaksi"
            else -> null
        }
    }
}
