package org.gdg.frisbee.android.utils;

import android.content.SharedPreferences;

import com.google.android.gms.plus.model.people.Person;

import java.util.Set;

import org.gdg.frisbee.android.fragment.InfoFragment;

public class OrganizerManager {
    private final InfoFragment infoFragment;

    public OrganizerManager(InfoFragment infoFragment) {
        this.infoFragment = infoFragment;
    }

    public void setUserAsOrganizer(Person person, String groupId) {
        SharedPreferences.Editor editor = infoFragment.getmPreferences().edit();

        if (infoFragment.getmPreferences().getBoolean(Const.SETTINGS_FIRST_ME_ORGANIZER, true)) {
            infoFragment.showCongratsToast(person);
            editor.putBoolean(Const.SETTINGS_FIRST_ME_ORGANIZER, false);
        }

        editor.putString(Const.SETTINGS_ME_ORGANIZER, person.getId());

        Set<String> groupIds = infoFragment.getmPreferences().getStringSet(Const.SETTINGS_ME_ORGANIZER_GDG_IDS, Collections.<String>emptySet());
        groupIds.add(groupId);
        editor.putStringSet(Const.SETTINGS_ME_ORGANIZER_GDG_IDS, groupIds);

        editor.apply();
    }
}