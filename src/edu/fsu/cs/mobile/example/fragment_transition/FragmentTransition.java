package edu.fsu.cs.mobile.example.fragment_transition;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FragmentTransition extends Activity {
    
	Button transition;
	FragmentManager manager;
	MyFragment fragment;
	FragmentTransaction ft;
	
	int[] transitions;
	
	int counter = 0;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        transitions = new int[6];
        transitions[0] = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        transitions[1] = FragmentTransaction.TRANSIT_FRAGMENT_CLOSE;
        transitions[2] = FragmentTransaction.TRANSIT_ENTER_MASK;
        transitions[3] = FragmentTransaction.TRANSIT_EXIT_MASK;
        transitions[4] = FragmentTransaction.TRANSIT_FRAGMENT_FADE;
        transitions[5] = FragmentTransaction.TRANSIT_NONE;
        
        manager = getFragmentManager();
        
        transition = (Button) findViewById(R.id.transition);
        
        transition.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				ft = manager.beginTransaction();
				
				if(counter % 2 == 0)
				{
					fragment = new MyFragment();
					ft.replace(R.id.fragment_container, fragment);
				}
				else
				{
					ft.remove(fragment);
				}
				
				Log.i("Transition","" + counter % 6);
				ft.setTransition(transitions[counter++ % 6]);
				ft.commit();
			}
		});
    }
}