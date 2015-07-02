package xyz.praveen.gitdroid;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.StatusCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectDatabase;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.RefDatabase;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;

import java.io.File;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            File repoDir = new File(Environment.getExternalStorageDirectory() + "/test/.git");
            Log.d("path", repoDir.getPath());
            //Git.init().setDirectory(repoDir).call();

            Repository repo = new FileRepository(repoDir);
            Git test = new Git(repo);
            Status cmd = test.status().call();
            Log.d("YOlo", cmd.isClean() ? "True" : "False");

            PersonIdent auth = new PersonIdent("Praveen Kumar", "m@praveen.xyz");
            test.add().addFilepattern("../testfile").call();
            test.commit().setMessage("YOlo COmmit").setAuthor(auth).setAll(true).call();

            cmd = test.status().call();
            Log.d("YOlo", cmd.isClean() ? "True" : "False");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
