package br.eng.crisjr.sheep.View;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.eng.crisjr.sheep.Model.Sheep;
import br.eng.crisjr.sheep.R;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by cris on 23/01/2016.
 */
public class MainView {

    /**
     * Fills the sheeps layout with sheep
     * @param context application context
     * @param layout layout that will have the sheep added to
     * @param sheeps list of sheep
     * @return layout filled with sheep
     */
    public static LinearLayout populateSheeps(Context context,
                                              LinearLayout layout,
                                              ArrayList<Sheep> sheeps)
    {
        for (Sheep sheep: sheeps)
        {
            String name = sheep.getName();
            int count = sheep.getCount();
            layout.addView(newSheep(context, name, count));
        }

        return layout;
    }

    /**
     * Fills the layout with sheeps ready to be changed
     * @param context application's context
     * @param layout the layout to be filled
     * @param sheeps the arraylist of sheeps
     * @return the filled layout
     */
    public static LinearLayout populateEmptySheeps(Context context,
                                                   LinearLayout layout,
                                                   ArrayList<Sheep> sheeps)
    {
        for (Sheep sheep : sheeps) {
            layout.addView(newSheepToFill(context, sheep.getName(), sheep.getCount()));
        }

        return layout;
    }

    /**
     * Adds a sheep with the defined information for filling
     * @param context The application's context
     * @param name The sheep's name
     * @param count The sheep's count
     * @return A layout containing the specified sheep
     */
    public static LinearLayout newSheepToFill(Context context, String name, int count)
    {
        LinearLayout layoutSheep = new LinearLayout(context);
        EditText textSheep = new EditText(context);

        textSheep.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                0.6f));
        textSheep.setText(name);
        textSheep.setTextColor(0xffeeeeee);
        textSheep.setBackgroundColor(0xff000000);
        layoutSheep.addView(textSheep);
        return populateSheep(context, layoutSheep, count);
    }

    /**
     * Adds a sheep with the defined information
     * @param context The application's context
     * @param name The sheep's name
     * @param count The sheep's count
     * @return A layout containing the specified sheep
     */
    public static LinearLayout newSheep(Context context, String name, int count)
    {
        LinearLayout layoutSheep = new LinearLayout(context);
        TextView textSheep = new TextView(context);

        textSheep.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                0.6f));
        textSheep.setText(name);
        textSheep.setTextColor(0xffeeeeee);
        textSheep.setBackgroundColor(0xff000000);
        layoutSheep.addView(textSheep);
        return populateSheep(context, layoutSheep, count);
    }

    /**
     * Creates an empty sheep
     * @param context the application context
     * @return an empty layout containing a sheep to be filled
     */
    public static LinearLayout newEmptySheep(Context context) {
        LinearLayout layoutSheep = new LinearLayout(context);
        EditText editSheep = new EditText(context);

        editSheep.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                0.6f));
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
    private static LinearLayout populateSheep(Context context, LinearLayout layoutSheep, int count)
    {
        TextView textCounter = new TextView(context);
        Button buttonMinus = new Button(context);
        Button buttonPlus = new Button(context);

        buttonMinus.setText("-");
        buttonMinus.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                  ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                  0.1f));
        textCounter.setText(new Integer(count).toString());
        textCounter.setTextColor(0xffeeeeee);
        textCounter.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                  ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                  0.2f));
        buttonPlus.setText("+");
        buttonPlus.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                  ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                  0.1f));

        layoutSheep.setOrientation(LinearLayout.HORIZONTAL);
        layoutSheep.addView(buttonMinus);
        layoutSheep.addView(textCounter);
        layoutSheep.addView(buttonPlus);
        return layoutSheep;
    }

    /**
     * Creates a list out a layout on the screen.
     * @param context the application context.
     * @param layoutSheeps the layout from which the method will extract the sheep.
     * @return an arraylist of the sheep currently on screen.
     */
    public static ArrayList<Sheep> extractSheeps(Context context, LinearLayout layoutSheeps)
    {
        ArrayList<Sheep> sheeps = new ArrayList<>();
        int howMany = layoutSheeps.getChildCount();

        for (int i = 1; i < howMany; ++i)
        {
            LinearLayout layoutSheep = (LinearLayout) layoutSheeps.getChildAt(i);
            String name = getNameFromLayout(layoutSheep);
            int count = getCountFromLayout(layoutSheep);

            if (name.length() >= 1) {
                Sheep sheep = new Sheep();
                sheep.setName(name);
                sheep.setCount(count);
                sheeps.add(sheep);
            }
        }

        return sheeps;
    }

    private static String getNameFromLayout(LinearLayout ls)
    {
        EditText edit = (EditText) ls.getChildAt(0);
        return edit.getText().toString();
    }

    private static int getCountFromLayout(LinearLayout ls)
    {
        TextView tv = (TextView) ls.getChildAt(2);
        return Integer.parseInt(tv.getText().toString());
    }

    /**
     * Removes every view but the desired button from the Sheeps layout
     * @param layoutSheeps layout to be dismembered
     */
    public static void removeEveryOtherView(LinearLayout layoutSheeps) {
        while (layoutSheeps.getChildCount() > 1)
        {
            layoutSheeps.removeViewAt(1);
        }
    }
}
