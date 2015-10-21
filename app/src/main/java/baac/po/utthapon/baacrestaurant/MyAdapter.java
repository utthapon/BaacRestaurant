package baac.po.utthapon.baacrestaurant;

import android.content.Context;
import android.hardware.Camera;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by BAAC on 21/10/2015.
 */
public class MyAdapter extends BaseAdapter{

    //explicit
    private Context objContext;
    private String[] sourceStrings , foodStrings , priceStrings;

    public MyAdapter(Context objContext, String[] sourceStrings, String[] foodStrings, String[] priceStrings) {
        this.objContext = objContext;
        this.sourceStrings = sourceStrings;
        this.foodStrings = foodStrings;
        this.priceStrings = priceStrings;
    }

    @Override
    public int getCount() {

        return foodStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View objView1 = objLayoutInflater.inflate(R.layout.food_listview,viewGroup,false);

        //for show food
        TextView foodTextView = (TextView) objView1.findViewById(R.id.txtsShowfood);
        foodTextView.setText(foodStrings[i]);

        //for show price
        TextView priceTextView = (TextView) objView1.findViewById(R.id.txtShowprice);
        priceTextView.setText(priceStrings[i]);

        //for show icon
        ImageView iconImageView = (ImageView) objView1.findViewById(R.id.imvicon);
        Picasso.with(objContext).load(sourceStrings[i]).resize(120,120).into(iconImageView);



        return objView1;
    }
}//main class
