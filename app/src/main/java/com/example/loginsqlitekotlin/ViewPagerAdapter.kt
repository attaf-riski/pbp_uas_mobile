import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.loginsqlitekotlin.fragment.holic.HolicTransaction
import com.example.loginsqlitekotlin.fragment.holic.Holicfragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> Holicfragment()
            1 -> HolicTransaction()
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
