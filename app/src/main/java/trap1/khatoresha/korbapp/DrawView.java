package trap1.khatoresha.korbapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RatingBar;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View{

    Paint paint=new Paint();
    int y=0,dY=13;//set initial y position and vertical speed
    int x=0,dX=15;//set initial x position and horiz speed
    Bitmap pink = BitmapFactory.decodeResource(getResources(), R.drawable.smallkorb);
    Bitmap blue = BitmapFactory.decodeResource(getResources(), R.drawable.bluekorb);
    Bitmap yellow = BitmapFactory.decodeResource(getResources(), R.drawable.yellowkorb);
    Bitmap b = pink;
    Bitmap space = BitmapFactory.decodeResource(getResources(), R.drawable.space);
    Bitmap starr =  BitmapFactory.decodeResource(getResources(), R.drawable.starr);
    Sprite star = new Sprite(10, 10, 110, 110, 5, 5, Color.YELLOW);
    RatingBar bar = findViewById(R.id.ratingBar);
    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        x=(int)(getWidth()*.5f-b.getWidth()/2);
        y=(int)(getHeight()*.5f-b.getHeight()/2);
        star.setBitmap(starr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.CYAN);//set paint to CYAN
        canvas.drawBitmap(space,0,0,paint);//paint background cyn
        canvas.drawBitmap(space,1200,0,paint);//paint background cyn
        //paint.setColor(Color.BLUE);//set paint
        //draw red circle nO
//        canvas.drawCircle(getWidth()*.5f,y,getWidth()*.3f,paint);
//        ImageView image = (ImageView)findViewById(R.id.image);
        //        image.setImageBitmap(b);
        canvas.drawBitmap(b,x,y,paint); //getWidth()*.5f-b.getWidth()/2
        star.update(canvas);
        //intersect code some point
        star.draw(canvas);
        if((x)<getLeft()){
            dX=-1*dX;
            randomColor();}
        if((x+(b.getWidth()))>=getRight()){
            dX=-1*dX;
            randomColor();}
        if((y)<0){
            dY=-1*dY;
            randomColor();}
        if((y+(b.getHeight()))>=getBottom()){ //could add dY and dXs
            dY=-1*dY;
            randomColor();}

        y+=dY;//increment y position
        x+=dX;//increment x position
        invalidate();  //redraws screen, invokes onDraw()
    }

    private Sprite generateSprite(){
        float x = (float)(Math.random()*(getWidth()-.1*getWidth()));
        float y = (float)(Math.random()*(getHeight()-.1*getHeight()));
        int dX = (int)(Math.random()*11-5);
        int dY = (int)(Math.random()*11-5);
        return new Sprite(x,y,x+.1f*getWidth(),y+.1f*getWidth(),dX,dY,Color.MAGENTA);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (star.contains(event.getX(), event.getY())) {
                star = generateSprite();
            }
        }
        return true;
    }

    public int getdX() {
        return dX;
    }
    public int getdY() {
        return dY;
    }
    public Bitmap getBitmap() { return b; }
    public void setBitmap(Bitmap bm) { b = bm; }
    public void randomColor()
    {
        //double d = Math.random();
        if(b==pink) setBitmap(yellow);
        else if(b==yellow) setBitmap(blue);
        else if(b==blue) setBitmap(pink);
    }

    public void faster (int i) {
        if(dX<0)
            dX -= i;
        else dX+=i;
        if(dY<0)
            dY -= i;
        else dY += i;
    }
    public void slower (int i) {
        if(dX<0)
            dX += i;
        else dX -=i;
        if(dY<0)
            dY += i;
        else dY -= i;
    }
}
