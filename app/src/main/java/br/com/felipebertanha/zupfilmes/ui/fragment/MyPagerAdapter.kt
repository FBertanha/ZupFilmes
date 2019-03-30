package br.com.felipebertanha.zupfilmes.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a FilmesFragment (defined as a static inner class below).
        if (position == 0) {
            return FilmesFragment()

        }
        return FavoritosFragment()
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 2
    }


}