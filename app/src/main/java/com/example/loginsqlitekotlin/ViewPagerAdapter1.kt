import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.loginsqlitekotlin.fragment.vanjava.VanjavaInfo
import com.example.loginsqlitekotlin.fragment.vanjava.VanjavaTransaction


class ViewPagerAdapter1(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> VanjavaInfo()
            1 -> VanjavaTransaction()
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
