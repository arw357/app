package app.sunrise.com.example.ciordache.sunrise;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import app.sunrise.com.example.ciordache.sunrise.R;

public class DetailActivity extends ActionBarActivity {
    static    ShareActionProvider mShareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        Intent i = getIntent();
        Bundle b = i.getExtras();
        String amprimit = b.getString("detail1","");

        Toast.makeText(getApplicationContext(),amprimit,Toast.LENGTH_LONG).show();





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void setmShareActionProvider(Intent shareIntent){
        if (mShareActionProvider!=null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            Intent i = getActivity().getIntent();
            Bundle b = i.getExtras();

            ((TextView) rootView.findViewById(R.id.detail_text)).setText(b.getString("forecast"));
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, b.getString("forecast",""));
            sendIntent.setType("text/plain");
            setmShareActionProvider(sendIntent);


            return rootView;
        }
    }
}
