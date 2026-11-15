package androidx.compose.foundation.text;

import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.PropertyReference1Impl;

/* JADX INFO: compiled from: KeyMapping.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u001c\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0000\"\u0014\u0010\u0006\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"commonKeyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "shortcutModifier", "Lkotlin/Function1;", "Landroidx/compose/ui/input/key/KeyEvent;", "", "defaultKeyMapping", "getDefaultKeyMapping", "()Landroidx/compose/foundation/text/KeyMapping;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class KeyMappingKt {
    private static final KeyMapping defaultKeyMapping;

    public static final KeyMapping commonKeyMapping(final Function1<? super KeyEvent, Boolean> function1) {
        return new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt.commonKeyMapping.1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* JADX INFO: renamed from: map-ZmokQxo */
            public KeyCommand mo1201mapZmokQxo(android.view.KeyEvent event) {
                if (function1.invoke(KeyEvent.m6236boximpl(event)).booleanValue() && KeyEvent_androidKt.m6259isShiftPressedZmokQxo(event)) {
                    if (Key.m5945equalsimpl0(KeyEvent_androidKt.m6253getKeyZmokQxo(event), Key.INSTANCE.m6231getZEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    return null;
                }
                if (function1.invoke(KeyEvent.m6236boximpl(event)).booleanValue()) {
                    long jM6253getKeyZmokQxo = KeyEvent_androidKt.m6253getKeyZmokQxo(event);
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m5999getCEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6063getInsertEK5gGoQ())) {
                        return KeyCommand.COPY;
                    }
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6220getVEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6228getXEK5gGoQ())) {
                        return KeyCommand.CUT;
                    }
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m5949getAEK5gGoQ())) {
                        return KeyCommand.SELECT_ALL;
                    }
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6229getYEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6231getZEK5gGoQ())) {
                        return KeyCommand.UNDO;
                    }
                    return null;
                }
                if (KeyEvent_androidKt.m6257isCtrlPressedZmokQxo(event)) {
                    return null;
                }
                if (KeyEvent_androidKt.m6259isShiftPressedZmokQxo(event)) {
                    long jM6253getKeyZmokQxo2 = KeyEvent_androidKt.m6253getKeyZmokQxo(event);
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6021getDirectionLeftEK5gGoQ())) {
                        return KeyCommand.SELECT_LEFT_CHAR;
                    }
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6022getDirectionRightEK5gGoQ())) {
                        return KeyCommand.SELECT_RIGHT_CHAR;
                    }
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6023getDirectionUpEK5gGoQ())) {
                        return KeyCommand.SELECT_UP;
                    }
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6018getDirectionDownEK5gGoQ())) {
                        return KeyCommand.SELECT_DOWN;
                    }
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6134getPageUpEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_UP;
                    }
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6133getPageDownEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_DOWN;
                    }
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6097getMoveHomeEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_START;
                    }
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6096getMoveEndEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_END;
                    }
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6063getInsertEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    return null;
                }
                long jM6253getKeyZmokQxo3 = KeyEvent_androidKt.m6253getKeyZmokQxo(event);
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6021getDirectionLeftEK5gGoQ())) {
                    return KeyCommand.LEFT_CHAR;
                }
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6022getDirectionRightEK5gGoQ())) {
                    return KeyCommand.RIGHT_CHAR;
                }
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6023getDirectionUpEK5gGoQ())) {
                    return KeyCommand.UP;
                }
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6018getDirectionDownEK5gGoQ())) {
                    return KeyCommand.DOWN;
                }
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6017getDirectionCenterEK5gGoQ())) {
                    return KeyCommand.CENTER;
                }
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6134getPageUpEK5gGoQ())) {
                    return KeyCommand.PAGE_UP;
                }
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6133getPageDownEK5gGoQ())) {
                    return KeyCommand.PAGE_DOWN;
                }
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6097getMoveHomeEK5gGoQ())) {
                    return KeyCommand.LINE_START;
                }
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6096getMoveEndEK5gGoQ())) {
                    return KeyCommand.LINE_END;
                }
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6031getEnterEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6123getNumPadEnterEK5gGoQ())) {
                    return KeyCommand.NEW_LINE;
                }
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m5962getBackspaceEK5gGoQ())) {
                    return KeyCommand.DELETE_PREV_CHAR;
                }
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6016getDeleteEK5gGoQ())) {
                    return KeyCommand.DELETE_NEXT_CHAR;
                }
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6136getPasteEK5gGoQ())) {
                    return KeyCommand.PASTE;
                }
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6014getCutEK5gGoQ())) {
                    return KeyCommand.CUT;
                }
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6011getCopyEK5gGoQ())) {
                    return KeyCommand.COPY;
                }
                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6181getTabEK5gGoQ())) {
                    return KeyCommand.TAB;
                }
                return null;
            }
        };
    }

    public static final KeyMapping getDefaultKeyMapping() {
        return defaultKeyMapping;
    }

    static {
        final KeyMapping common = commonKeyMapping(new PropertyReference1Impl() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object receiver0) {
                return Boolean.valueOf(KeyEvent_androidKt.m6257isCtrlPressedZmokQxo(((KeyEvent) receiver0).m6242unboximpl()));
            }
        });
        defaultKeyMapping = new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$2$1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* JADX INFO: renamed from: map-ZmokQxo */
            public KeyCommand mo1201mapZmokQxo(android.view.KeyEvent event) {
                KeyCommand keyCommand = null;
                if (KeyEvent_androidKt.m6259isShiftPressedZmokQxo(event) && KeyEvent_androidKt.m6257isCtrlPressedZmokQxo(event)) {
                    long jM6253getKeyZmokQxo = KeyEvent_androidKt.m6253getKeyZmokQxo(event);
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6021getDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LEFT_WORD;
                    } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6022getDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_RIGHT_WORD;
                    } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6023getDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_PREV_PARAGRAPH;
                    } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6018getDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_NEXT_PARAGRAPH;
                    }
                } else if (KeyEvent_androidKt.m6257isCtrlPressedZmokQxo(event)) {
                    long jM6253getKeyZmokQxo2 = KeyEvent_androidKt.m6253getKeyZmokQxo(event);
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6021getDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.LEFT_WORD;
                    } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6022getDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.RIGHT_WORD;
                    } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6023getDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.PREV_PARAGRAPH;
                    } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6018getDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.NEXT_PARAGRAPH;
                    } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6056getHEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_CHAR;
                    } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6016getDeleteEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_NEXT_WORD;
                    } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m5962getBackspaceEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_WORD;
                    } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m5961getBackslashEK5gGoQ())) {
                        keyCommand = KeyCommand.DESELECT;
                    }
                } else if (KeyEvent_androidKt.m6259isShiftPressedZmokQxo(event)) {
                    long jM6253getKeyZmokQxo3 = KeyEvent_androidKt.m6253getKeyZmokQxo(event);
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6097getMoveHomeEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_START;
                    } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo3, Key.INSTANCE.m6096getMoveEndEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_END;
                    }
                } else if (KeyEvent_androidKt.m6256isAltPressedZmokQxo(event)) {
                    long jM6253getKeyZmokQxo4 = KeyEvent_androidKt.m6253getKeyZmokQxo(event);
                    if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo4, Key.INSTANCE.m5962getBackspaceEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_FROM_LINE_START;
                    } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo4, Key.INSTANCE.m6016getDeleteEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_TO_LINE_END;
                    }
                }
                if (keyCommand != null) {
                    return keyCommand;
                }
                return common.mo1201mapZmokQxo(event);
            }
        };
    }
}
