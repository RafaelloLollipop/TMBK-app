package info.androidhive.tabsswipe.adapter;
 
import com.example.beacontour.BeaconsFragment;
import com.example.beacontour.DetailsFragment;
import com.example.beacontour.MapFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * Adapter class form fragment manager 
 * @author Adam, Ante, rafal
 *
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
            return new MapFragment();
        case 1:
            // Games fragment activity
            return new BeaconsFragment();
        case 2:
            // Movies fragment activity
            return new DetailsFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}