package com.gemvietnam.widget;

import com.gemvietnam.common.R;
import com.gemvietnam.utils.StringUtils;
import com.gemvietnam.utils.Typefaces;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.appcompat.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Custom Text view
 * Created by neo on 3/24/2016.
 */
public class CEditText extends AppCompatEditText {
  public CEditText(Context context) {
    super(context);
    initFont(null);
  }

  public CEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    initFont(attrs);
  }

  public CEditText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initFont(attrs);
  }

  /**
   * init font text view
   */
  public void initFont(AttributeSet attrs) {
    if (attrs == null) {
      return;
    }

    TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CTextView);
    String font = a.getString(R.styleable.CTextView_typeFaceFont);
    String style = a.getString(R.styleable.CTextView_typeFaceStyle);

    if (font != null) {
      if (!StringUtils.isEmpty(style)) {
        font = font + "-" + style;
      }
      this.setTypeface(Typefaces.get(getContext(), font, "ttf"));
    }
    a.recycle();
  }

}