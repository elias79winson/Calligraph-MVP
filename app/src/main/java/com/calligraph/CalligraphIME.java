package com.calligraph;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.View;

public class CalligraphIME extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv;
    private Keyboard keyboard;

    // Массив всех символов, например, Unicode-глифы или кириллические буквы
    private final String[] letters = {
        "А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "Й", "К",
        "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х",
        "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я", "𐰀", "𐰃", "𐰆"
    };

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView) getLayoutInflater().inflate(R.layout.layout_keyboard, null);
        keyboard = new Keyboard(this, R.xml.method); // method.xml содержит keyCodes
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;

        if (primaryCode == Keyboard.KEYCODE_DELETE) {
            ic.deleteSurroundingText(1, 0);
        } else if (primaryCode == Keyboard.KEYCODE_DONE) {
            ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
        } else {
            // Преобразуем код в букву из массива
            int index = primaryCode; // предполагаем, что primaryCode — индекс
            if (index >= 0 && index < letters.length) {
                ic.commitText(letters[index], 1);
            }
        }
    }

    @Override public void onPress(int primaryCode) {}
    @Override public void onRelease(int primaryCode) {}
    @Override public void onText(CharSequence text) {}
    @Override public void swipeLeft() {}
    @Override public void swipeRight() {}
    @Override public void swipeDown() {}
    @Override public void swipeUp() {}
}
