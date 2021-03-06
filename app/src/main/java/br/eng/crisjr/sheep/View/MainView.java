package br.eng.crisjr.sheep.View;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import br.eng.crisjr.sheep.Controller.Sheeps;
import br.eng.crisjr.sheep.Model.Sheep;
import java.util.ArrayList;
import java.util.Random;

/**
 * View class for the main activity.
 */
public class MainView
{
    private LinearLayout layoutSheeps = null;
    private Context context = null;
    private Sheeps controller = new Sheeps();
    private ScreenUtil screen = new ScreenUtil();
    private int screenWidth = 0;

    public MainView()
    {

    }

    public MainView(Context context)
    {
        this.context = context;
        this.controller = new Sheeps(this.context);
    }

    /**
     * Fills the sheeps layout with sheep
     * @param context application context
     * @param layout layout that will have the sheep added to
     * @return layout filled with sheep
     */
    public LinearLayout populateSheeps(Context context,
                                       LinearLayout layout)
    {
        layoutSheeps = layout;
        screen = new ScreenUtil(context);
        screenWidth = screen.convertPixtoDip(screen.getScreenWidth());

        for (Sheep sheep: controller.getSheeps())
        {
            String name = sheep.getName();
            int count = sheep.getCount();
            layout.addView(newSheep(context, name, count));
        }

        this.layoutSheeps = layout;
        this.context = context;
        return layout;
    }

    /**
     * Adds a sheep with the defined information
     * @param context The application's context
     * @param name The sheep's name
     * @param count The sheep's count
     * @return A layout containing the specified sheep
     */
    public LinearLayout newSheep(Context context, String name, int count)
    {
        LinearLayout layoutSheep = new LinearLayout(context);
        TextView textSheep = new TextView(context);

        textSheep.setLayoutParams(new LinearLayout.LayoutParams(screenWidth/2,
                                                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                0.5f));
        textSheep.setText(name);
        textSheep.setTextColor(0xffeeeeee);
        textSheep.setBackgroundColor(0xff000000);
        layoutSheep.addView(textSheep);
        return populateSheep(context, layoutSheep, count);
    }

    /**
     * Fills the layout with sheeps ready to be changed
     * @param context application's context
     * @param layout the layout to be filled
     * @return the filled layout
     */
    public LinearLayout populateEmptySheeps(Context context,
                                            LinearLayout layout)
    {
        screen = new ScreenUtil(context);
        screenWidth = screen.convertPixtoDip(screen.getScreenWidth());

        for (Sheep sheep : controller.getSheeps())
        {
            layout.addView(newSheepToFill(context, sheep.getName(), sheep.getCount()));
        }

        this.layoutSheeps = layout;
        this.context = context;
        return layout;
    }

    /**
     * Adds a sheep with the defined information for filling
     * @param context The application's context
     * @param name The sheep's name
     * @param count The sheep's count
     * @return A layout containing the specified sheep
     */
    public LinearLayout newSheepToFill(Context context, String name, int count)
    {
        LinearLayout layoutSheep = new LinearLayout(context);
        EditText textSheep = new EditText(context);

        textSheep.setLayoutParams(new LinearLayout.LayoutParams(screenWidth/2,
                                                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                0.5f));
        textSheep.setText(name);
        textSheep.setTextColor(0xffeeeeee);
        textSheep.setBackgroundColor(0xff000000);
        textSheep.setImeOptions(EditorInfo.IME_ACTION_DONE);
        layoutSheep.addView(textSheep);
        return populateSheep(context, layoutSheep, count);
    }

    /**
     * Creates an empty sheep
     * @param context the application context
     * @return an empty layout containing a sheep to be filled
     */
    public LinearLayout newSheep(Context context)
    {
        LinearLayout layoutSheep = new LinearLayout(context);
        EditText editSheep = new EditText(context);

        editSheep.setLayoutParams(new LinearLayout.LayoutParams(50*screenWidth/100,
                                                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                0.5f));
        layoutSheep.addView(editSheep);
        return populateSheep(context, layoutSheep, 0);
    }

    /**
     * Creates the rest of a sheep, regardless of its type
     * @param context application's context
     * @param layoutSheep sheep's layout
     * @param count counting measure
     * @return the filled layoutSheep
     */
    private LinearLayout populateSheep(Context context, LinearLayout layoutSheep, int count)
    {
        Random random = new Random();
        TextView textCounter = new TextView(context);
        Button buttonMinus = new Button(context);
        Button buttonPlus = new Button(context);

        buttonMinus.setTypeface(IconicConstants.getTypeface());
        buttonMinus.setText(IconicConstants.LEFT);
        buttonMinus.setGravity(Gravity.CENTER);
        buttonMinus.setBackgroundColor(0xff000000);
        buttonMinus.setTextColor(0xffeeeeee);
        buttonMinus.setId(random.nextInt());
        buttonMinus.setLayoutParams(new LinearLayout.LayoutParams(13*screenWidth/100,
                                                                  ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                  0.13f));
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count((Button) v, -1);
            }
        });
        textCounter.setText(screen.itos(count));
        textCounter.setTextColor(0xffeeeeee);
        textCounter.setGravity(Gravity.CENTER);
        textCounter.setLayoutParams(new LinearLayout.LayoutParams(24*screenWidth/100,
                                                                  ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                  0.24f));
        buttonPlus.setTypeface(IconicConstants.getTypeface());
        buttonPlus.setText(IconicConstants.RIGHT);
        buttonPlus.setGravity(Gravity.CENTER);
        buttonPlus.setBackgroundColor(0xff000000);
        buttonPlus.setTextColor(0xffeeeeee);
        buttonPlus.setId(random.nextInt());
        buttonPlus.setLayoutParams(new LinearLayout.LayoutParams(13*screenWidth/100,
                                                                 ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                 0.13f));
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count((Button) v, +1);
            }
        });

        layoutSheep.setOrientation(LinearLayout.HORIZONTAL);
        layoutSheep.addView(buttonMinus);
        layoutSheep.addView(textCounter);
        layoutSheep.addView(buttonPlus);
        this.context = context;
        return layoutSheep;
    }

    /**
     * Apply a step to a counter
     * @param button the button that will serve as
     * @param step how much the counter must walk
     */
    private void count(Button button, int step)
    {
        int index = getSheepIndexWithButton(button);
        LinearLayout sheep = (LinearLayout) layoutSheeps.getChildAt(index);
        TextView tv = (TextView) sheep.getChildAt(2);
        int count = Integer.parseInt(tv.getText().toString());
        tv.setText(screen.itos(count + step));
        store();
    }

    /**
     * Creates a list out a layout on the screen.
     * @param layoutSheeps the layout from which the method will extract the sheep.
     * @return an arraylist of the sheep currently on screen.
     */
    public ArrayList<Sheep> extractSheeps(LinearLayout layoutSheeps)
    {
        ArrayList<Sheep> sheeps = new ArrayList<>();
        int howMany = layoutSheeps.getChildCount();

        for (int i = 0; i < howMany; ++i)
        {
            LinearLayout layoutSheep = (LinearLayout) layoutSheeps.getChildAt(i);
            String name = getNameFromLayout(layoutSheep);
            int count = getCountFromLayout(layoutSheep);
            sheeps.add(controller.createSheep(name, count));
        }

        controller.setSheeps(sheeps);
        return sheeps;
    }

    private String getNameFromLayout(LinearLayout ls)
    {
        String outlet = null;

        try {
            EditText edit = (EditText) ls.getChildAt(0);
            outlet = edit.getText().toString();
        }
        catch (ClassCastException cc) {
            TextView text = (TextView) ls.getChildAt(0);
            outlet = text.getText().toString();
        }

        return outlet;
    }

    private int getCountFromLayout(LinearLayout ls)
    {
        TextView tv = (TextView) ls.getChildAt(2);
        return Integer.parseInt(tv.getText().toString());
    }

    /**
     * Removes every view but the desired button from the Sheeps layout
     * @param layoutSheeps layout to be dismembered
     */
    public void removeEveryOtherView(LinearLayout layoutSheeps)
    {
        while (layoutSheeps.getChildCount() > 0)
        {
            layoutSheeps.removeViewAt(0);
        }
    }

    /**
     * Creates a sheeps' layout ready for remotion
     * @param context application's context
     * @param ls layout to be edited
     */
    public void populateFilledSheeps(Context context,
                                     LinearLayout ls)
    {
        for (Sheep sheep: controller.getSheeps())
        {
            ls.addView(createFilledSheep(context, sheep));
        }

        layoutSheeps = ls;
    }

    private LinearLayout createFilledSheep(Context context, Sheep sheep)
    {
        Random random = new Random();
        LinearLayout layout = new LinearLayout(context);
        TextView tvn = new TextView(context);
        TextView tvc = new TextView(context);
        Button bt = new Button(context);

        tvn.setText(sheep.getName());
        tvn.setTextColor(0xffeeeeee);
        tvc.setText(screen.itos(sheep.getCount()));
        tvc.setGravity(Gravity.CENTER);
        tvc.setTextColor(0xffeeeeee);
        bt.setText(IconicConstants.DELETE);
        bt.setTypeface(IconicConstants.getTypeface());
        bt.setBackgroundColor(0xff000000);
        bt.setTextColor(0xfff93822);
        bt.setId(random.nextInt());
        bt.setClickable(true);
        bt.setGravity(Gravity.CENTER);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSheepWithButton((Button) v);
            }
        });

        tvn.setLayoutParams(new LinearLayout.LayoutParams(screenWidth/2,
                                                          ViewGroup.LayoutParams.WRAP_CONTENT,
                                                          0.5f));
        tvc.setLayoutParams(new LinearLayout.LayoutParams(screenWidth/4,
                                                          ViewGroup.LayoutParams.WRAP_CONTENT,
                                                          0.25f));
        bt.setLayoutParams(new LinearLayout.LayoutParams(screenWidth/4,
                                                         ViewGroup.LayoutParams.WRAP_CONTENT,
                                                         0.25f));

        layout.addView(tvn);
        layout.addView(bt);
        layout.addView(tvc);

        return layout;
    }

    private void deleteSheepWithButton(Button button)
    {
        int index = getSheepIndexWithButton(button);
        layoutSheeps.removeViewAt(index);
        store();
    }

    private int getSheepIndexWithButton(Button toFind)
    {
        int result = -1;

        for (int i = 0; i < layoutSheeps.getChildCount() && result < 0; i++)
        {
            try {
                LinearLayout sheep = (LinearLayout) layoutSheeps.getChildAt(i);
                Button current = (Button) sheep.getChildAt(1);
                if (current.getId() == toFind.getId()) {
                    result = i;
                }
                current = (Button) sheep.getChildAt(3);
                if (current.getId() == toFind.getId()) {
                    result = i;
                }
            }
            catch (NullPointerException bacon) {
                pass();
            }
        }

        return result;
    }

    /**
     * Setter for layoutSheeps
     * @param layoutSheeps the current layout used on screen
     */
    public void setLayoutSheeps(LinearLayout layoutSheeps) {
        this.layoutSheeps = layoutSheeps;
    }

    /**
     * Gets how many sheep are stored right now.
     * @return number of sheep stored.
     */
    public int getSheepSize()
    {
        return controller.getSheeps().size();
    }

    private void pass() { }

    /**
     * Saves the sheep to memory
     */
    public void store()
    {
        extractSheeps(this.layoutSheeps);
        controller.store(this.context);
    }

    /**
     * Retrieves the current sheep on memory
     */
    public void retrieve()
    {
        controller.retrieve(this.context);
    }

    /**
     * Retrieves sheep from memory based on applocation context
     * @param context application context
     */
    public void retrieve(Context context)
    {
        controller.retrieve(context);
    }
}
