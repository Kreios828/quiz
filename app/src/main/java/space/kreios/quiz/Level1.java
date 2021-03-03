package space.kreios.quiz;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level1 extends AppCompatActivity {

    public int numLeft; //переменная для левой картинки + текст
    public int numRight;   //переменная для правой картинки + текст
    public int count = 0; //Счетчик правильных ответов
    Dialog dialog;
    Dialog dialogEnd; //создание диалогового окна для вызова в конце уровня
    Array array = new Array(); //Создали новый объект класса Array
    Random random = new Random(); // Для генерации случайных чисел

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //создание переменной text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);

        final ImageView img_left = findViewById(R.id.img_left);
        //скругление углов левой картинки
        img_left.setClipToOutline(true);

        final ImageView img_right = findViewById(R.id.img_right);
        //скругление углов правой картинки
        img_right.setClipToOutline(true);

        //Путь к левой TextView
        final TextView text_left = findViewById(R.id.text_left);
        //Путь к левой TextView
        final TextView text_right = findViewById(R.id.text_right);

        //Развернуть игру на весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //Развернуть игру на весь экран - конец

        //Вызов диалогового окна в начале игры
        dialog = new Dialog(this); //создаем новое диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialog.setContentView(R.layout.previewdialog); //путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон диалогового окна
        dialog.setCancelable(false); //запрет на закрытие кнопкой назад

        //кнопка закрытия диалогового окна в чачале игры - начало
        TextView btnclose = dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // обработка нажатия кнопки - начало
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class); //Создали намерение для перехода
                    startActivity(intent); //старт намерения
                    finish(); //закрыть этот класс
                } catch (Exception e) {
                }
                dialog.dismiss(); // закрываем диалоговое окно
                // обработка нажатия кнопки - конец
            }
        });
        //кнопка закрытия диалогового окна - конец

        //Кнопка продолжить - начало
        Button bntcontinue = dialog.findViewById(R.id.btncontinue);
        bntcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); //Закрываем диалоговое окно
            }
        });
        //Кнопка продолжить - конец

        dialog.show(); // показ диалогового окна

        //________________________________________
        //Вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this); //создаем новое диалоговое окно
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialogEnd.setContentView(R.layout.dialogend); //путь к макету диалогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон диалогового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT); //разворот диалогового окна на весь экран
        dialogEnd.setCancelable(false); //запрет на закрытие кнопкой назад

        //кнопка закрытия диалогового окна в чачале игры - начало
        TextView btnclose2 = dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // обработка нажатия кнопки - начало
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class); //Создали намерение для перехода
                    startActivity(intent); //старт намерения
                    finish(); //закрыть этот класс
                } catch (Exception e) {
                }
                dialogEnd.dismiss(); // закрываем диалоговое окно
                // обработка нажатия кнопки - конец
            }
        });
        //кнопка закрытия диалогового окна - конец

        //Кнопка продолжить - начало
        Button bntcontinue2 = dialogEnd.findViewById(R.id.btncontinue);
        bntcontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, Level2.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }

                dialogEnd.dismiss(); //Закрываем диалоговое окно
            }
        });
        //Кнопка продолжить - конец

        //________________________________________

        //Кнопка назад - начало
        Button btnback = findViewById(R.id.button_back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Обработка нажатия кнопки назад - начало
                try {
                    // Вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level1.this, GameLevels.class); //создание намерения для перехода
                    startActivity(intent); //Старт намерения
                    finish(); // Закрыть этот класс
                    // Вернуться назад к выбору уровня - конец
                } catch (Exception e) {
                }
                //Обработка нажатия кнопки назад - конец
            }
        });
        //Кнопка назад - конец

        //Массив для прогресса игры - начало
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6,
                R.id.point7, R.id.point8, R.id.point9, R.id.point10, R.id.point11, R.id.point12,
                R.id.point13, R.id.point14, R.id.point15, R.id.point16, R.id.point17, R.id.point18,
                R.id.point19, R.id.point20,
        };
        //Массив для прогресса игры - конец

        //Подключаем анимацию - начало
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);
        //Подключаем анимацию - конец

        numLeft = random.nextInt(10); //генерация случайного числа от 0 до 9 включ.
        img_left.setImageResource(array.images1[numLeft]); //Достаем из массива картинку
        text_left.setText(array.texts1[numLeft]);//Достаем из массива текст

        numRight = random.nextInt(10); //генерация случайного числа от 0 до 9 включ.

        //цикл с предусловием, проверяющий на равенство чисел - начало
        while (numLeft == numRight) {
            numRight = random.nextInt(10);
        }
        //цикл с предусловием, проверяющий на равенство чисел - конец

        img_right.setImageResource(array.images1[numRight]); // Достаем из массива картинку
        text_right.setText(array.texts1[numRight]); //Достаем из массива текст

        //Обработка нажатия на левую кнопку - начало
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условие касания картинки - начало
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Если коснулся картинки - начало
                    img_right.setEnabled(false);  //блокируем правую картинку
                    if (numLeft > numRight) {
                        img_left.setImageResource(R.drawable.img_true);
                    } else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                    //Если коснулся картинки - конец
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Если отпустил палец - начало
                    if (numLeft > numRight) {
                        // Если левая картинка больше
                        if (count < 20) {
                            count = count + 1;
                        }

                        // Закрашивание прогресса серым цветом - начало
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашивание прогресса серым цветом - конец

                        // Определение правильных ответов и закрашивание зеленым - начало
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        // Определение правильных ответов и закрашивание зеленым - конец
                    } else {
                        // Если левая картинка меньше
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                        }
                        // Закрашивание прогресса серым цветом - начало
                        for (int i = 0; i < 19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашивание прогресса серым цветом - конец

                        // Определение правильных ответов и закрашивание зеленым - начало
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        // Определение правильных ответов и закрашивание зеленым - конец
                    }
                    //Если отпустил палец - конец
                    if (count == 20) {
                        //ВЫХОД ИЗ УРОВНЯ
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level1", 1);
                        if (level > 1) {
                            //пусто
                        } else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 2);
                            editor.commit();
                        }
                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(10); //генерация случайного числа от 0 до 9 включ.
                        img_left.setImageResource(array.images1[numLeft]); //Достаем из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]);//Достаем из массива текст

                        numRight = random.nextInt(10); //генерация случайного числа от 0 до 9 включ.

                        //цикл с предусловием, проверяющий на равенство чисел - начало
                        while (numLeft == numRight) {
                            numRight = random.nextInt(10);
                        }
                        //цикл с предусловием, проверяющий на равенство чисел - конец

                        img_right.setImageResource(array.images1[numRight]); // Достаем из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]); //Достаем из массива текст

                        img_right.setEnabled(true); //включаем обратно правую картинку
                    }
                }
                //Условие касания картинки - конец

                return true;
            }
        });
        //Обработка нажатия на левую кнопку - конец

        //Обработка нажатия на правую кнопку - начало
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условие касания картинки - начало
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Если коснулся картинки - начало
                    img_left.setEnabled(false);  //блокируем левую картинку
                    if (numLeft < numRight) {
                        img_right.setImageResource(R.drawable.img_true);
                    } else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                    //Если коснулся картинки - конец
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Если отпустил палец - начало
                    if (numLeft < numRight) {
                        // Если левая картинка больше
                        if (count < 20) {
                            count = count + 1;
                        }

                        // Закрашивание прогресса серым цветом - начало
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашивание прогресса серым цветом - конец

                        // Определение правильных ответов и закрашивание зеленым - начало
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        // Определение правильных ответов и закрашивание зеленым - конец
                    } else {
                        // Если левая картинка меньше
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                        }
                        // Закрашивание прогресса серым цветом - начало
                        for (int i = 0; i < 19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашивание прогресса серым цветом - конец

                        // Определение правильных ответов и закрашивание зеленым - начало
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        // Определение правильных ответов и закрашивание зеленым - конец
                    }
                    //Если отпустил палец - конец
                    if (count == 20) {
                        //ВЫХОД ИЗ УРОВНЯ
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level1", 1);
                        if (level > 1) {
                            //пусто
                        } else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 2);
                            editor.commit();
                        }
                        dialogEnd.show();
                    } else {
                        numRight = random.nextInt(10); //генерация случайного числа от 0 до 9 включ.
                        img_right.setImageResource(array.images1[numRight]); //Достаем из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]);//Достаем из массива текст

                        numLeft = random.nextInt(10); //генерация случайного числа от 0 до 9 включ.

                        //цикл с предусловием, проверяющий на равенство чисел - начало
                        while (numLeft == numRight) {
                            numLeft = random.nextInt(10);
                        }
                        //цикл с предусловием, проверяющий на равенство чисел - конец

                        img_left.setImageResource(array.images1[numLeft]); // Достаем из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]); //Достаем из массива текст

                        img_left.setEnabled(true); //включаем обратно левую картинку
                    }
                }
                //Условие касания картинки - конец

                return true;
            }
        });
        //Обработка нажатия на правую кнопку - конец

    }

    //Системная кнопка назад - начало
    @Override
    public void onBackPressed() {
        //Обработка нажатия кнопки назад - начало
        try {
            // Вернуться назад к выбору уровня - начало
            Intent intent = new Intent(Level1.this, GameLevels.class); //создание намерения для перехода
            startActivity(intent); //Старт намерения
            finish(); // Закрыть этот класс
            // Вернуться назад к выбору уровня - конец
        } catch (Exception e) {
        }
        //Обработка нажатия кнопки назад - конец
    }
    //Системная кнопка назад - конец
}