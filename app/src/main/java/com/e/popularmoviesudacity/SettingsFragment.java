package com.e.popularmoviesudacity;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements
        SharedPreferences.OnSharedPreferenceChangeListener {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);


        //logic to set a summary for the sort order preference selected
        SharedPreferences sharedPreferences =getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen = getPreferenceScreen();

        int count = prefScreen.getPreferenceCount();

        for (int i = 0; i < count; i++) {
            Preference p = prefScreen.getPreference(i);
            if (!(p instanceof CheckBoxPreference)) {

            String value = sharedPreferences.getString(p.getKey(), getString(R.string.most_popular));

                // helper method to set the summary of the list preference
                setPreferenceSummary(p, value);
            }
        }

    }

    private void setPreferenceSummary(Preference p, String value) {

        ListPreference listPreference = (ListPreference) p;
        int prefIndex = listPreference.findIndexOfValue(value);
        if (prefIndex >= 0) {
            // Set the summary to that label
            listPreference.setSummary(listPreference.getEntries()[prefIndex]);
        }

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        //get the preference changed
        Preference preference = findPreference(s);
        String value = sharedPreferences.getString(preference.getKey(), "");
        setPreferenceSummary(preference, value);

    }


    // COMPLETED (5) Register and unregister the OnSharedPreferenceChange listener (this class) in
    // onCreate and onDestroy respectively.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
