package org.gdg.frisbee.android.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.plus.model.people.Person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.gdg.frisbee.android.Const;
import org.gdg.frisbee.android.R;

import de.keyboardsurfer.android.widget.crouton.Crouton;

public class OrganizerManager {

    public static void setUserAsOrganizer(Activity activity, SharedPreferences prefs, Person person, String groupId) {
        SharedPreferences.Editor editor = prefs.edit();

        if (prefs.getBoolean(Const.SETTINGS_FIRST_ME_ORGANIZER, true)) {
            showCongratsToast(activity, person);
            editor.putBoolean(Const.SETTINGS_FIRST_ME_ORGANIZER, false);
        }

        editor.putString(Const.SETTINGS_ME_ORGANIZER, person.getId());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            Set<String> groupIds = prefs.getStringSet(Const.SETTINGS_ME_ORGANIZER_GDG_IDS, Collections.<String>emptySet());
            HashSet<String> newGroupIds = new HashSet<String>();
            newGroupIds.addAll(groupIds);
            newGroupIds.add(groupId);
            editor.putStringSet(Const.SETTINGS_ME_ORGANIZER_GDG_IDS, newGroupIds);
        } else {
            editor.putString(Const.SETTINGS_ME_ORGANIZER_GDG_IDS, groupId);
        }

        editor.apply();
    }

    private static void showCongratsToast(Activity activity, com.google.android.gms.plus.model.people.Person person) {
        View view = activity.getLayoutInflater().inflate(R.layout.view_congrats, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText(activity.getString(R.string.congrats, person.getDisplayName()));
        Crouton.show(activity, view);
    }

    public static boolean isOrganizer(SharedPreferences prefs) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return prefs.getStringSet(Const.SETTINGS_ME_ORGANIZER_GDG_IDS,
                Collections.<String>emptySet()).size() > 0;
        } else {
            return prefs.getString(Const.SETTINGS_ME_ORGANIZER_GDG_IDS, null) != null;
        }

    }
}