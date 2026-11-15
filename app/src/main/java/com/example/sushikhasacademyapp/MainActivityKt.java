package com.example.sushikhasacademyapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.autofill.HintConstants;
import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.AnimationModifierKt;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteractionKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.FlowLayoutKt;
import androidx.compose.foundation.layout.FlowRowScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.LazyListStateKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.MenuKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.ArrowBackKt;
import androidx.compose.material.icons.automirrored.filled.ArrowForwardKt;
import androidx.compose.material.icons.filled.AccountCircleKt;
import androidx.compose.material.icons.filled.BookKt;
import androidx.compose.material.icons.filled.CallKt;
import androidx.compose.material.icons.filled.CameraAltKt;
import androidx.compose.material.icons.filled.CampaignKt;
import androidx.compose.material.icons.filled.CheckCircleKt;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material.icons.filled.EmailKt;
import androidx.compose.material.icons.filled.InfoKt;
import androidx.compose.material.icons.filled.LocationOnKt;
import androidx.compose.material.icons.filled.PersonAddKt;
import androidx.compose.material.icons.filled.PersonKt;
import androidx.compose.material.icons.filled.PhoneKt;
import androidx.compose.material.icons.filled.ScheduleKt;
import androidx.compose.material.icons.filled.SchoolKt;
import androidx.compose.material.icons.filled.StarKt;
import androidx.compose.material.icons.filled.VerifiedKt;
import androidx.compose.material.icons.filled.VisibilityKt;
import androidx.compose.material.icons.filled.VisibilityOffKt;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardColors;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardElevation;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.DatePickerDialog_androidKt;
import androidx.compose.material3.DatePickerKt;
import androidx.compose.material3.DatePickerState;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.ExposedDropdownMenuBoxScope;
import androidx.compose.material3.ExposedDropdownMenuDefaults;
import androidx.compose.material3.ExposedDropdownMenuKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldDefaults;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.location.LocationRequestCompat;
import androidx.core.net.MailTo;
import androidx.media3.common.C;
import androidx.profileinstaller.ProfileVerifier;
import coil.compose.SingletonAsyncImageKt;
import com.cloudinary.provisioning.Account;
import com.example.sushikhasacademyapp.data.BatchSchedule;
import com.example.sushikhasacademyapp.data.ContactInfo;
import com.example.sushikhasacademyapp.data.CourseInfo;
import com.example.sushikhasacademyapp.data.DictionaryData;
import com.example.sushikhasacademyapp.data.ProfessionalWord;
import com.example.sushikhasacademyapp.data.SqlConnectRepository;
import com.example.sushikhasacademyapp.data.TeacherProfile;
import com.example.sushikhasacademyapp.data.Testimonial;
import com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt;
import com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt;
import com.example.sushikhasacademyapp.ui.theme.ThemeKt;
import com.example.sushikhasacademyapp.utils.TextToSpeechHelper;
import com.facebook.imagepipeline.common.RotationOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.logging.type.LogSeverity;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\u001a\u0019\u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a#\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010\n\u001a\u0015\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u000e\u001a%\u0010\u000f\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010\u0011\u001a%\u0010\u0012\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010\u0011\u001a)\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\r2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u0017H\u0007¢\u0006\u0002\u0010\u0018\u001a\u001d\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u001c\u001a#\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010!\u001a#\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010&\u001a\u0015\u0010'\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u000e\u001a%\u0010)\u001a\u00020\u00012\u0006\u0010*\u001a\u00020+2\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010,\u001a\u00020\rH\u0007¢\u0006\u0002\u0010-\u001a#\u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u0002002\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u00102\u001a\u0015\u00103\u001a\u00020\u00012\u0006\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u00104\u001a\u001d\u00105\u001a\u00020\u00012\u0006\u00106\u001a\u0002072\u0006\u0010(\u001a\u00020\rH\u0007¢\u0006\u0002\u00108\u001ax\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\r2\u0006\u0010(\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\t2K\u0010;\u001aG\u0012\u0013\u0012\u00110\r¢\u0006\f\b=\u0012\b\b>\u0012\u0004\b\b(>\u0012\u0013\u0012\u00110\r¢\u0006\f\b=\u0012\b\b>\u0012\u0004\b\b(?\u0012\u0013\u0012\u00110\r¢\u0006\f\b=\u0012\b\b>\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00010<H\u0007¢\u0006\u0002\u0010@\u001a\r\u0010A\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010B\u001aP\u0010C\u001a\u00020\u00012\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020\r2\u0006\u0010G\u001a\u00020\r2\u0006\u0010H\u001a\u00020\r2\u0006\u0010I\u001a\u00020\r2\u0006\u0010J\u001a\u00020\r2\u0006\u0010K\u001a\u00020\r2\u0006\u0010L\u001a\u00020\r2\b\b\u0002\u0010M\u001a\u00020N\u001aE\u0010O\u001a\u00020\u000126\u0010P\u001a2\u0012\u0013\u0012\u00110N¢\u0006\f\b=\u0012\b\b>\u0012\u0004\b\b(R\u0012\u0013\u0012\u00110\r¢\u0006\f\b=\u0012\b\b>\u0012\u0004\b\b(H\u0012\u0004\u0012\u00020\u00010QH\u0007¢\u0006\u0002\u0010S\u001a\r\u0010T\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010B\u001a\r\u0010U\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010B¨\u0006V²\u0006\n\u0010W\u001a\u00020NX\u008a\u008e\u0002²\u0006\n\u0010X\u001a\u00020NX\u008a\u008e\u0002²\u0006\n\u0010Y\u001a\u00020\rX\u008a\u008e\u0002²\u0006\u0010\u0010Z\u001a\b\u0012\u0004\u0012\u00020\r0[X\u008a\u008e\u0002²\u0006\n\u0010\u0006\u001a\u00020\u0007X\u008a\u0084\u0002²\u0006\u0010\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u001f0[X\u008a\u0084\u0002²\u0006\u0010\u0010]\u001a\b\u0012\u0004\u0012\u00020$0[X\u008a\u0084\u0002²\u0006\n\u00106\u001a\u000207X\u008a\u0084\u0002²\u0006\n\u0010^\u001a\u00020NX\u008a\u0084\u0002²\u0006\n\u0010\u0015\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010_\u001a\u00020NX\u008a\u008e\u0002²\u0006\n\u0010`\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010a\u001a\u00020NX\u008a\u008e\u0002²\u0006\n\u0010b\u001a\u00020NX\u008a\u008e\u0002²\u0006\u001e\u0010c\u001a\u0016\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010dX\u008a\u008e\u0002²\u0006\f\u0010e\u001a\u0004\u0018\u00010\rX\u008a\u008e\u0002²\u0006\n\u0010f\u001a\u00020gX\u008a\u0084\u0002²\u0006\n\u0010h\u001a\u00020gX\u008a\u0084\u0002²\u0006\n\u0010i\u001a\u00020jX\u008a\u008e\u0002²\u0006\n\u0010k\u001a\u00020NX\u008a\u008e\u0002²\u0006\n\u0010l\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010m\u001a\u00020gX\u008a\u0084\u0002²\u0006\n\u0010n\u001a\u00020oX\u008a\u0084\u0002²\u0006\n\u0010p\u001a\u00020oX\u008a\u0084\u0002²\u0006\n\u0010q\u001a\u00020NX\u008a\u0084\u0002²\u0006\n\u0010r\u001a\u00020NX\u008a\u008e\u0002²\u0006\n\u0010s\u001a\u00020gX\u008a\u0084\u0002²\u0006\n\u0010q\u001a\u00020NX\u008a\u0084\u0002²\u0006\n\u0010s\u001a\u00020gX\u008a\u0084\u0002²\u0006\n\u0010t\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010u\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010v\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010w\u001a\u00020gX\u008a\u0084\u0002²\u0006\n\u0010x\u001a\u00020NX\u008a\u008e\u0002²\u0006\n\u0010H\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010y\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010z\u001a\u00020NX\u008a\u008e\u0002²\u0006\n\u0010G\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010I\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010{\u001a\u00020NX\u008a\u008e\u0002²\u0006\n\u0010|\u001a\u00020NX\u008a\u008e\u0002²\u0006\n\u0010J\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010}\u001a\u00020NX\u008a\u008e\u0002²\u0006\n\u0010~\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010K\u001a\u00020\rX\u008a\u008e\u0002²\u0006\r\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010\u0081\u0001\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010^\u001a\u00020NX\u008a\u008e\u0002"}, d2 = {"AcademyAppScreen", "", "ttsHelper", "Lcom/example/sushikhasacademyapp/utils/TextToSpeechHelper;", "(Lcom/example/sushikhasacademyapp/utils/TextToSpeechHelper;Landroidx/compose/runtime/Composer;II)V", "TeacherProfileCard", "teacherProfile", "Lcom/example/sushikhasacademyapp/data/TeacherProfile;", "onInquireClick", "Lkotlin/Function0;", "(Lcom/example/sushikhasacademyapp/data/TeacherProfile;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "ExpertiseTag", "text", "", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "WordOfTheDayCard", "onOpenDictionaryBrowser", "(Lcom/example/sushikhasacademyapp/utils/TextToSpeechHelper;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "DictionaryBrowserDialog", "onDismiss", "CategoryFilterBar", "selectedCategory", "onCategorySelected", "Lkotlin/Function1;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "SectionHeader", "title", "subtitle", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "CourseCard", "course", "Lcom/example/sushikhasacademyapp/data/CourseInfo;", "onEnrollClick", "(Lcom/example/sushikhasacademyapp/data/CourseInfo;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "BatchCard", "batch", "Lcom/example/sushikhasacademyapp/data/BatchSchedule;", "onSelectBatch", "(Lcom/example/sushikhasacademyapp/data/BatchSchedule;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "WhyChooseUsSection", "teacherName", "FeatureItem", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "description", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "FeaturedGoogleReviewCard", "testimonial", "Lcom/example/sushikhasacademyapp/data/Testimonial;", "onOpenGoogleReviews", "(Lcom/example/sushikhasacademyapp/data/Testimonial;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "TestimonialCard", "(Lcom/example/sushikhasacademyapp/data/Testimonial;Landroidx/compose/runtime/Composer;I)V", "ContactFooterCard", "contactInfo", "Lcom/example/sushikhasacademyapp/data/ContactInfo;", "(Lcom/example/sushikhasacademyapp/data/ContactInfo;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "InquiryDialog", "initialCourse", "onSubmit", "Lkotlin/Function3;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "phone", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "LiveSketchBackground", "(Landroidx/compose/runtime/Composer;I)V", "saveUserToFirestore", "context", "Landroid/content/Context;", "uid", HintConstants.AUTOFILL_HINT_USERNAME, "email", "dob", "studentClass", "school", "exam", "isNewUser", "", "LoginScreen", "onLoginSuccess", "Lkotlin/Function2;", "isAdmin", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "AcademyAppScreenPreview", "TeacherProfileCardPreview", "app", "isLoggedIn", "isAdminLoggedIn", "loggedInEmail", "enrolledBatchesState", "", "courses", "batches", "isLoading", "showInquiryDialog", "selectedCourseForInquiry", "showAdminDashboardDialog", "showDictionaryBrowser", "latestBatchNotice", "Lkotlin/Triple;", "savedProfilePath", "pulseAlpha", "", "pulseScale", "currentWordIndex", "", "isSpeaking", "searchQuery", "scale", "containerColor", "Landroidx/compose/ui/graphics/Color;", "labelColor", "isPressed", "isExpanded", "cardScale", "studentName", "phoneNo", "courseName", TypedValues.CycleType.S_WAVE_PHASE, "isSignUp", "password", "passwordVisible", "showDatePicker", "expandedClass", "expandedExam", "selectedExam", "profileUri", "Landroid/net/Uri;", "errorMessage"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class MainActivityKt {
    static final Unit AcademyAppScreen$lambda$136(TextToSpeechHelper textToSpeechHelper, int i, int i2, Composer composer, int i3) {
        AcademyAppScreen(textToSpeechHelper, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AcademyAppScreen$lambda$18(TextToSpeechHelper textToSpeechHelper, int i, int i2, Composer composer, int i3) {
        AcademyAppScreen(textToSpeechHelper, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AcademyAppScreenPreview$lambda$470(int i, Composer composer, int i2) {
        AcademyAppScreenPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit BatchCard$lambda$237(BatchSchedule batchSchedule, Function0 function0, int i, Composer composer, int i2) {
        BatchCard(batchSchedule, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit CategoryFilterBar$lambda$203(String str, Function1 function1, int i, Composer composer, int i2) {
        CategoryFilterBar(str, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ContactFooterCard$lambda$285(ContactInfo contactInfo, String str, int i, Composer composer, int i2) {
        ContactFooterCard(contactInfo, str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit CourseCard$lambda$225(CourseInfo courseInfo, Function0 function0, int i, Composer composer, int i2) {
        CourseCard(courseInfo, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DictionaryBrowserDialog$lambda$194(TextToSpeechHelper textToSpeechHelper, Function0 function0, int i, Composer composer, int i2) {
        DictionaryBrowserDialog(textToSpeechHelper, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ExpertiseTag$lambda$152(String str, int i, Composer composer, int i2) {
        ExpertiseTag(str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit FeatureItem$lambda$244(ImageVector imageVector, String str, String str2, int i, Composer composer, int i2) {
        FeatureItem(imageVector, str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit FeaturedGoogleReviewCard$lambda$254(Testimonial testimonial, Function0 function0, int i, Composer composer, int i2) {
        FeaturedGoogleReviewCard(testimonial, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit InquiryDialog$lambda$309(String str, String str2, Function0 function0, Function3 function3, int i, Composer composer, int i2) {
        InquiryDialog(str, str2, function0, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit LiveSketchBackground$lambda$313(int i, Composer composer, int i2) {
        LiveSketchBackground(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit LoginScreen$lambda$469(Function2 function2, int i, Composer composer, int i2) {
        LoginScreen(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SectionHeader$lambda$205(String str, String str2, int i, Composer composer, int i2) {
        SectionHeader(str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TeacherProfileCard$lambda$150(TeacherProfile teacherProfile, Function0 function0, int i, Composer composer, int i2) {
        TeacherProfileCard(teacherProfile, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TeacherProfileCardPreview$lambda$471(int i, Composer composer, int i2) {
        TeacherProfileCardPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TestimonialCard$lambda$263(Testimonial testimonial, int i, Composer composer, int i2) {
        TestimonialCard(testimonial, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit WhyChooseUsSection$lambda$240(String str, int i, Composer composer, int i2) {
        WhyChooseUsSection(str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit WordOfTheDayCard$lambda$178(TextToSpeechHelper textToSpeechHelper, Function0 function0, int i, Composer composer, int i2) {
        WordOfTheDayCard(textToSpeechHelper, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:130:0x05fb  */
    /* JADX WARN: Code duplicated, block: B:132:0x0628  */
    /* JADX WARN: Code duplicated, block: B:133:0x0636  */
    /* JADX WARN: Code duplicated, block: B:139:0x0664  */
    /* JADX WARN: Code duplicated, block: B:141:0x0680  */
    /* JADX WARN: Code duplicated, block: B:144:0x0693  */
    /* JADX WARN: Code duplicated, block: B:146:0x06c7  */
    /* JADX WARN: Code duplicated, block: B:147:0x06da  */
    /* JADX WARN: Code duplicated, block: B:149:0x06ff  */
    /* JADX WARN: Code duplicated, block: B:152:0x0712  */
    /* JADX WARN: Code duplicated, block: B:154:0x0734  */
    /* JADX WARN: Code duplicated, block: B:155:0x0742  */
    /* JADX WARN: Code duplicated, block: B:157:0x0752  */
    /* JADX WARN: Code duplicated, block: B:160:0x0763  */
    public static final void AcademyAppScreen(TextToSpeechHelper ttsHelper, Composer $composer, final int $changed, final int i) {
        String str;
        final MutableState isAdminLoggedIn$delegate;
        MutableState latestBatchNotice$delegate;
        final Context context;
        final TextToSpeechHelper ttsHelper2;
        String str2;
        final State teacherProfile$delegate;
        Composer composer;
        Object objRememberedValue;
        Object objRememberedValue2;
        Object obj;
        Object objRememberedValue3;
        final MutableState showInquiryDialog$delegate;
        boolean zChangedInstance;
        Object objRememberedValue4;
        String email;
        final TextToSpeechHelper ttsHelper3 = ttsHelper;
        Composer $composer2 = $composer.startRestartGroup(233694204);
        ComposerKt.sourceInformation($composer2, "C(AcademyAppScreen)N(ttsHelper)95@3989L7,96@4022L24,97@4068L35,99@4120L39,100@4182L87,101@4297L56,102@4379L124,104@4537L54,106@4627L1466,106@4597L1496,155@6400L16,156@6455L16,157@6510L16,158@6573L16,159@6632L16,161@6678L42,162@6750L34,163@6821L31,164@6889L34,165@6957L34,167@7016L689,175@7733L21,176@7786L23,177@7846L23,179@7900L66,180@7992L35,182@8070L2061,182@8033L2098,221@10164L4264,312@14461L806,331@15274L16409,220@10137L21546:MainActivity.kt#t7vdsr");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(ttsHelper3) : $composer2.changedInstance(ttsHelper3) ? 4 : 2;
        }
        if ($composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            if (i2 != 0) {
                ttsHelper3 = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(233694204, $dirty, -1, "com.example.sushikhasacademyapp.AcademyAppScreen (MainActivity.kt:94)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Context context2 = (Context) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer2, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue5 = $composer2.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object objCreateCompositionCoroutineScope = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2);
                $composer2.updateRememberedValue(objCreateCompositionCoroutineScope);
                objRememberedValue5 = objCreateCompositionCoroutineScope;
            }
            final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241837855, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue6 = $composer2.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                Object sqlConnectRepository = new SqlConnectRepository();
                $composer2.updateRememberedValue(sqlConnectRepository);
                objRememberedValue6 = sqlConnectRepository;
            }
            final SqlConnectRepository repository = (SqlConnectRepository) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241839523, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue7 = $composer2.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                Object firebaseAuth = FirebaseAuth.getInstance();
                $composer2.updateRememberedValue(firebaseAuth);
                objRememberedValue7 = firebaseAuth;
            }
            FirebaseAuth auth = (FirebaseAuth) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Intrinsics.checkNotNull(auth);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241841555, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue8 = $composer2.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(LoginPrefs.INSTANCE.isLoggedIn(context2) || auth.getCurrentUser() != null), null, 2, null);
                $composer2.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue8 = objMutableStateOf$default;
            }
            final MutableState isLoggedIn$delegate = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241845204, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue9 = $composer2.rememberedValue();
            if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(LoginPrefs.INSTANCE.isAdmin(context2)), null, 2, null);
                $composer2.updateRememberedValue(objMutableStateOf$default2);
                objRememberedValue9 = objMutableStateOf$default2;
            }
            final MutableState isAdminLoggedIn$delegate2 = (MutableState) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241847896, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue10 = $composer2.rememberedValue();
            if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                String userEmail = LoginPrefs.INSTANCE.getUserEmail(context2);
                if (StringsKt.isBlank(userEmail)) {
                    FirebaseUser currentUser = auth.getCurrentUser();
                    if (currentUser == null || (email = currentUser.getEmail()) == null) {
                        email = "student@sushiksha.com";
                    }
                    userEmail = email;
                }
                Object objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(userEmail, null, 2, null);
                $composer2.updateRememberedValue(objMutableStateOf$default3);
                objRememberedValue10 = objMutableStateOf$default3;
            }
            final MutableState loggedInEmail$delegate = (MutableState) objRememberedValue10;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241852882, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue11 = $composer2.rememberedValue();
            if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(CollectionsKt.emptyList(), null, 2, null);
                $composer2.updateRememberedValue(objMutableStateOf$default4);
                objRememberedValue11 = objMutableStateOf$default4;
            }
            final MutableState enrolledBatchesState$delegate = (MutableState) objRememberedValue11;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            String strAcademyAppScreen$lambda$10 = AcademyAppScreen$lambda$10(loggedInEmail$delegate);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241857174, "CC(remember):MainActivity.kt#9igjgp");
            boolean zChangedInstance2 = $composer2.changedInstance(auth);
            Object objRememberedValue12 = $composer2.rememberedValue();
            if (zChangedInstance2 || objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = (Function2) new MainActivityKt$AcademyAppScreen$1$1(auth, loggedInEmail$delegate, enrolledBatchesState$delegate, null);
                $composer2.updateRememberedValue(obj2);
                objRememberedValue12 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(strAcademyAppScreen$lambda$10, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue12, $composer2, 0);
            if (!AcademyAppScreen$lambda$3(isLoggedIn$delegate)) {
                $composer2.startReplaceGroup(-155694496);
                ComposerKt.sourceInformation($composer2, "146@6168L147,145@6126L199");
                ComposerKt.sourceInformationMarkerStart($composer2, 1241905167, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue13 = $composer2.rememberedValue();
                if (objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                    Object obj3 = new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj4, Object obj5) {
                            return MainActivityKt.AcademyAppScreen$lambda$17$lambda$16(isAdminLoggedIn$delegate2, loggedInEmail$delegate, isLoggedIn$delegate, ((Boolean) obj4).booleanValue(), (String) obj5);
                        }
                    };
                    $composer2.updateRememberedValue(obj3);
                    objRememberedValue13 = obj3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LoginScreen((Function2) objRememberedValue13, $composer2, 6);
                $composer2.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj4, Object obj5) {
                            return MainActivityKt.AcademyAppScreen$lambda$18(ttsHelper3, $changed, i, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            $composer2.startReplaceGroup(-161769690);
            $composer2.endReplaceGroup();
            final State teacherProfile$delegate2 = SnapshotStateKt.collectAsState(repository.getTeacherProfile(), null, $composer2, 0, 1);
            final State courses$delegate = SnapshotStateKt.collectAsState(repository.getCourses(), null, $composer2, 0, 1);
            final State batches$delegate = SnapshotStateKt.collectAsState(repository.getBatches(), null, $composer2, 0, 1);
            final State contactInfo$delegate = SnapshotStateKt.collectAsState(repository.getContactInfo(), null, $composer2, 0, 1);
            final State isLoading$delegate = SnapshotStateKt.collectAsState(repository.isLoading(), null, $composer2, 0, 1);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241921382, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue14 = $composer2.rememberedValue();
            if (objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("All Courses", null, 2, null);
                $composer2.updateRememberedValue(objMutableStateOf$default5);
                objRememberedValue14 = objMutableStateOf$default5;
            }
            final MutableState selectedCategory$delegate = (MutableState) objRememberedValue14;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241923678, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue15 = $composer2.rememberedValue();
            if (objRememberedValue15 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer2.updateRememberedValue(objMutableStateOf$default6);
                objRememberedValue15 = objMutableStateOf$default6;
            }
            final MutableState showInquiryDialog$delegate2 = (MutableState) objRememberedValue15;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241925947, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue16 = $composer2.rememberedValue();
            if (objRememberedValue16 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer2.updateRememberedValue(objMutableStateOf$default7);
                objRememberedValue16 = objMutableStateOf$default7;
            }
            final MutableState selectedCourseForInquiry$delegate = (MutableState) objRememberedValue16;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241928126, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue17 = $composer2.rememberedValue();
            if (objRememberedValue17 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer2.updateRememberedValue(objMutableStateOf$default8);
                objRememberedValue17 = objMutableStateOf$default8;
            }
            final MutableState showAdminDashboardDialog$delegate = (MutableState) objRememberedValue17;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241930302, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue18 = $composer2.rememberedValue();
            if (objRememberedValue18 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer2.updateRememberedValue(objMutableStateOf$default9);
                objRememberedValue18 = objMutableStateOf$default9;
            }
            final MutableState showDictionaryBrowser$delegate = (MutableState) objRememberedValue18;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241932845, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue19 = $composer2.rememberedValue();
            if (objRememberedValue19 == Composer.INSTANCE.getEmpty()) {
                Object objListOf = CollectionsKt.listOf((Object[]) new Testimonial[]{new Testimonial("Ankita", "Basic English", "I presently study English in Sushiksha Academy under Suman Sir's guidance. I hope this academy is the top English Academy in Konnagar as so many students are passing every years with letter marks.", 5, null, false, null, 112, null), new Testimonial("Agnimitra Saha", "Board batch student", "Excellent teaching! The concepts are explained so clearly and the study environment is really motivating. Highly recommended for all students.", 5, null, false, null, 112, null), new Testimonial("Debolina Mitra", "Spoken English", "Great ambience to learn English easily. Not just the grammar rules but also the speaking fluency is remarkable", 5, null, false, null, 112, null)});
                $composer2.updateRememberedValue(objListOf);
                objRememberedValue19 = objListOf;
            }
            final List testimonials = (List) objRememberedValue19;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final ScrollState mainScrollState = ScrollKt.rememberScrollState(0, $composer2, 0, 1);
            final LazyListState batchesLazyListState = LazyListStateKt.rememberLazyListState(0, 0, $composer2, 0, 3);
            final LazyListState testimonialsLazyListState = LazyListStateKt.rememberLazyListState(0, 0, $composer2, 0, 3);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241960510, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue20 = $composer2.rememberedValue();
            if (objRememberedValue20 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default10 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer2.updateRememberedValue(objMutableStateOf$default10);
                objRememberedValue20 = objMutableStateOf$default10;
            }
            MutableState latestBatchNotice$delegate2 = (MutableState) objRememberedValue20;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241963423, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue21 = $composer2.rememberedValue();
            if (objRememberedValue21 == Composer.INSTANCE.getEmpty()) {
                Object obj4 = (Set) new LinkedHashSet();
                $composer2.updateRememberedValue(obj4);
                objRememberedValue21 = obj4;
            }
            Set notifiedDocIds = (Set) objRememberedValue21;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            List<String> listAcademyAppScreen$lambda$13 = AcademyAppScreen$lambda$13(enrolledBatchesState$delegate);
            ComposerKt.sourceInformationMarkerStart($composer2, 1241967945, "CC(remember):MainActivity.kt#9igjgp");
            boolean zChangedInstance3 = $composer2.changedInstance(notifiedDocIds) | $composer2.changedInstance(context2);
            Object objRememberedValue22 = $composer2.rememberedValue();
            if (zChangedInstance3) {
                str = "CC(remember):MainActivity.kt#9igjgp";
            } else {
                str = "CC(remember):MainActivity.kt#9igjgp";
                if (objRememberedValue22 != Composer.INSTANCE.getEmpty()) {
                    latestBatchNotice$delegate = latestBatchNotice$delegate2;
                    isAdminLoggedIn$delegate = isAdminLoggedIn$delegate2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(listAcademyAppScreen$lambda$13, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue22, $composer2, 0);
                context = context2;
                final MutableState isAdminLoggedIn$delegate3 = isAdminLoggedIn$delegate;
                ttsHelper2 = ttsHelper3;
                final MutableState latestBatchNotice$delegate3 = latestBatchNotice$delegate;
                str2 = str;
                ScaffoldKt.m2765ScaffoldTvnljyQ(null, ComposableLambdaKt.rememberComposableLambda(-1150007616, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj5, Object obj6) {
                        return MainActivityKt.AcademyAppScreen$lambda$63(mainScrollState, teacherProfile$delegate2, contactInfo$delegate, context, isAdminLoggedIn$delegate, showAdminDashboardDialog$delegate, loggedInEmail$delegate, (Composer) obj5, ((Integer) obj6).intValue());
                    }
                }, $composer2, 54), null, null, ComposableLambdaKt.rememberComposableLambda(-1585527997, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj5, Object obj6) {
                        return MainActivityKt.AcademyAppScreen$lambda$67(mainScrollState, selectedCourseForInquiry$delegate, showInquiryDialog$delegate2, (Composer) obj5, ((Integer) obj6).intValue());
                    }
                }, $composer2, 54), 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(1431090187, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj5, Object obj6, Object obj7) {
                        return MainActivityKt.AcademyAppScreen$lambda$127(mainScrollState, latestBatchNotice$delegate3, context, isAdminLoggedIn$delegate3, coroutineScope, repository, teacherProfile$delegate2, selectedCourseForInquiry$delegate, showInquiryDialog$delegate2, ttsHelper2, showDictionaryBrowser$delegate, selectedCategory$delegate, courses$delegate, isLoading$delegate, batches$delegate, batchesLazyListState, enrolledBatchesState$delegate, testimonialsLazyListState, testimonials, contactInfo$delegate, (PaddingValues) obj5, (Composer) obj6, ((Integer) obj7).intValue());
                    }
                }, $composer2, 54), $composer2, 805330992, 493);
                $composer2 = $composer2;
                if (AcademyAppScreen$lambda$28(showInquiryDialog$delegate2)) {
                    $composer2.startReplaceGroup(-130271830);
                    ComposerKt.sourceInformation($composer2, "674@31884L29,675@31938L320,671@31744L524");
                    String strAcademyAppScreen$lambda$31 = AcademyAppScreen$lambda$31(selectedCourseForInquiry$delegate);
                    String name = AcademyAppScreen$lambda$19(teacherProfile$delegate2).getName();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1242727961, str2);
                    objRememberedValue3 = $composer2.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        showInquiryDialog$delegate = showInquiryDialog$delegate2;
                        Object obj5 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda11
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MainActivityKt.AcademyAppScreen$lambda$129$lambda$128(showInquiryDialog$delegate);
                            }
                        };
                        $composer2.updateRememberedValue(obj5);
                        objRememberedValue3 = obj5;
                    } else {
                        showInquiryDialog$delegate = showInquiryDialog$delegate2;
                    }
                    Function0 function0 = (Function0) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1242729980, str2);
                    teacherProfile$delegate = teacherProfile$delegate2;
                    zChangedInstance = $composer2.changedInstance(context) | $composer2.changed(teacherProfile$delegate);
                    objRememberedValue4 = $composer2.rememberedValue();
                    if (zChangedInstance || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        Object obj6 = new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda12
                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(Object obj7, Object obj8, Object obj9) {
                                return MainActivityKt.AcademyAppScreen$lambda$131$lambda$130(context, showInquiryDialog$delegate, teacherProfile$delegate, (String) obj7, (String) obj8, (String) obj9);
                            }
                        };
                        $composer2.updateRememberedValue(obj6);
                        objRememberedValue4 = obj6;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    InquiryDialog(strAcademyAppScreen$lambda$31, name, function0, (Function3) objRememberedValue4, $composer2, 384);
                } else {
                    teacherProfile$delegate = teacherProfile$delegate2;
                    $composer2.startReplaceGroup(-161769690);
                }
                $composer2.endReplaceGroup();
                if (AcademyAppScreen$lambda$34(showAdminDashboardDialog$delegate)) {
                    $composer2.startReplaceGroup(-129678397);
                    ComposerKt.sourceInformation($composer2, "694@32579L36,688@32350L275");
                    TeacherProfile teacherProfileAcademyAppScreen$lambda$19 = AcademyAppScreen$lambda$19(teacherProfile$delegate);
                    List<CourseInfo> listAcademyAppScreen$lambda$20 = AcademyAppScreen$lambda$20(courses$delegate);
                    List<BatchSchedule> listAcademyAppScreen$lambda$21 = AcademyAppScreen$lambda$21(batches$delegate);
                    ContactInfo contactInfoAcademyAppScreen$lambda$22 = AcademyAppScreen$lambda$22(contactInfo$delegate);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1242750208, str2);
                    objRememberedValue2 = $composer2.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        Object obj7 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda13
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MainActivityKt.AcademyAppScreen$lambda$133$lambda$132(showAdminDashboardDialog$delegate);
                            }
                        };
                        $composer2.updateRememberedValue(obj7);
                        obj = obj7;
                    } else {
                        obj = objRememberedValue2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    AdminDashboardDialogKt.AdminDashboardDialog(repository, teacherProfileAcademyAppScreen$lambda$19, listAcademyAppScreen$lambda$20, listAcademyAppScreen$lambda$21, contactInfoAcademyAppScreen$lambda$22, (Function0) obj, $composer2, SqlConnectRepository.$stable | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (TeacherProfile.$stable << 3) | (ContactInfo.$stable << 12));
                    $composer2 = $composer2;
                } else {
                    $composer2.startReplaceGroup(-161769690);
                }
                $composer2.endReplaceGroup();
                if (AcademyAppScreen$lambda$37(showDictionaryBrowser$delegate)) {
                    $composer2.startReplaceGroup(-129317929);
                    ComposerKt.sourceInformation($composer2, "702@32802L33,700@32718L127");
                    ComposerKt.sourceInformationMarkerStart($composer2, 1242757341, str2);
                    composer = $composer2;
                    objRememberedValue = composer.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object obj8 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MainActivityKt.AcademyAppScreen$lambda$135$lambda$134(showDictionaryBrowser$delegate);
                            }
                        };
                        composer.updateRememberedValue(obj8);
                        objRememberedValue = obj8;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    DictionaryBrowserDialog(ttsHelper2, (Function0) objRememberedValue, $composer2, ($dirty & 14) | 48);
                } else {
                    $composer2.startReplaceGroup(-161769690);
                }
                $composer2.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ttsHelper3 = ttsHelper2;
            }
            isAdminLoggedIn$delegate = isAdminLoggedIn$delegate2;
            Object mainActivityKt$AcademyAppScreen$4$1 = new MainActivityKt$AcademyAppScreen$4$1(enrolledBatchesState$delegate, notifiedDocIds, context2, latestBatchNotice$delegate2, isAdminLoggedIn$delegate, null);
            context2 = context2;
            latestBatchNotice$delegate = latestBatchNotice$delegate2;
            Object obj9 = (Function2) mainActivityKt$AcademyAppScreen$4$1;
            $composer2.updateRememberedValue(obj9);
            objRememberedValue22 = obj9;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(listAcademyAppScreen$lambda$13, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue22, $composer2, 0);
            context = context2;
            final MutableState isAdminLoggedIn$delegate4 = isAdminLoggedIn$delegate;
            ttsHelper2 = ttsHelper3;
            final MutableState latestBatchNotice$delegate4 = latestBatchNotice$delegate;
            str2 = str;
            ScaffoldKt.m2765ScaffoldTvnljyQ(null, ComposableLambdaKt.rememberComposableLambda(-1150007616, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj10, Object obj11) {
                    return MainActivityKt.AcademyAppScreen$lambda$63(mainScrollState, teacherProfile$delegate2, contactInfo$delegate, context, isAdminLoggedIn$delegate, showAdminDashboardDialog$delegate, loggedInEmail$delegate, (Composer) obj10, ((Integer) obj11).intValue());
                }
            }, $composer2, 54), null, null, ComposableLambdaKt.rememberComposableLambda(-1585527997, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj10, Object obj11) {
                    return MainActivityKt.AcademyAppScreen$lambda$67(mainScrollState, selectedCourseForInquiry$delegate, showInquiryDialog$delegate2, (Composer) obj10, ((Integer) obj11).intValue());
                }
            }, $composer2, 54), 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(1431090187, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj10, Object obj11, Object obj12) {
                    return MainActivityKt.AcademyAppScreen$lambda$127(mainScrollState, latestBatchNotice$delegate4, context, isAdminLoggedIn$delegate4, coroutineScope, repository, teacherProfile$delegate2, selectedCourseForInquiry$delegate, showInquiryDialog$delegate2, ttsHelper2, showDictionaryBrowser$delegate, selectedCategory$delegate, courses$delegate, isLoading$delegate, batches$delegate, batchesLazyListState, enrolledBatchesState$delegate, testimonialsLazyListState, testimonials, contactInfo$delegate, (PaddingValues) obj10, (Composer) obj11, ((Integer) obj12).intValue());
                }
            }, $composer2, 54), $composer2, 805330992, 493);
            $composer2 = $composer2;
            if (AcademyAppScreen$lambda$28(showInquiryDialog$delegate2)) {
                $composer2.startReplaceGroup(-130271830);
                ComposerKt.sourceInformation($composer2, "674@31884L29,675@31938L320,671@31744L524");
                String strAcademyAppScreen$lambda$32 = AcademyAppScreen$lambda$31(selectedCourseForInquiry$delegate);
                String name2 = AcademyAppScreen$lambda$19(teacherProfile$delegate2).getName();
                ComposerKt.sourceInformationMarkerStart($composer2, 1242727961, str2);
                objRememberedValue3 = $composer2.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    showInquiryDialog$delegate = showInquiryDialog$delegate2;
                    Object obj10 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.AcademyAppScreen$lambda$129$lambda$128(showInquiryDialog$delegate);
                        }
                    };
                    $composer2.updateRememberedValue(obj10);
                    objRememberedValue3 = obj10;
                } else {
                    showInquiryDialog$delegate = showInquiryDialog$delegate2;
                }
                Function0 function1 = (Function0) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, 1242729980, str2);
                teacherProfile$delegate = teacherProfile$delegate2;
                zChangedInstance = $composer2.changedInstance(context) | $composer2.changed(teacherProfile$delegate);
                objRememberedValue4 = $composer2.rememberedValue();
                if (zChangedInstance) {
                    Object obj11 = new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj12, Object obj13, Object obj14) {
                            return MainActivityKt.AcademyAppScreen$lambda$131$lambda$130(context, showInquiryDialog$delegate, teacherProfile$delegate, (String) obj12, (String) obj13, (String) obj14);
                        }
                    };
                    $composer2.updateRememberedValue(obj11);
                    objRememberedValue4 = obj11;
                } else {
                    Object obj12 = new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj13, Object obj14, Object obj15) {
                            return MainActivityKt.AcademyAppScreen$lambda$131$lambda$130(context, showInquiryDialog$delegate, teacherProfile$delegate, (String) obj13, (String) obj14, (String) obj15);
                        }
                    };
                    $composer2.updateRememberedValue(obj12);
                    objRememberedValue4 = obj12;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                InquiryDialog(strAcademyAppScreen$lambda$32, name2, function1, (Function3) objRememberedValue4, $composer2, 384);
            } else {
                teacherProfile$delegate = teacherProfile$delegate2;
                $composer2.startReplaceGroup(-161769690);
            }
            $composer2.endReplaceGroup();
            if (AcademyAppScreen$lambda$34(showAdminDashboardDialog$delegate)) {
                $composer2.startReplaceGroup(-129678397);
                ComposerKt.sourceInformation($composer2, "694@32579L36,688@32350L275");
                TeacherProfile teacherProfileAcademyAppScreen$lambda$110 = AcademyAppScreen$lambda$19(teacherProfile$delegate);
                List<CourseInfo> listAcademyAppScreen$lambda$22 = AcademyAppScreen$lambda$20(courses$delegate);
                List<BatchSchedule> listAcademyAppScreen$lambda$23 = AcademyAppScreen$lambda$21(batches$delegate);
                ContactInfo contactInfoAcademyAppScreen$lambda$23 = AcademyAppScreen$lambda$22(contactInfo$delegate);
                ComposerKt.sourceInformationMarkerStart($composer2, 1242750208, str2);
                objRememberedValue2 = $composer2.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj13 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.AcademyAppScreen$lambda$133$lambda$132(showAdminDashboardDialog$delegate);
                        }
                    };
                    $composer2.updateRememberedValue(obj13);
                    obj = obj13;
                } else {
                    obj = objRememberedValue2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                AdminDashboardDialogKt.AdminDashboardDialog(repository, teacherProfileAcademyAppScreen$lambda$110, listAcademyAppScreen$lambda$22, listAcademyAppScreen$lambda$23, contactInfoAcademyAppScreen$lambda$23, (Function0) obj, $composer2, SqlConnectRepository.$stable | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (TeacherProfile.$stable << 3) | (ContactInfo.$stable << 12));
                $composer2 = $composer2;
            } else {
                $composer2.startReplaceGroup(-161769690);
            }
            $composer2.endReplaceGroup();
            if (AcademyAppScreen$lambda$37(showDictionaryBrowser$delegate)) {
                $composer2.startReplaceGroup(-129317929);
                ComposerKt.sourceInformation($composer2, "702@32802L33,700@32718L127");
                ComposerKt.sourceInformationMarkerStart($composer2, 1242757341, str2);
                composer = $composer2;
                objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj14 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.AcademyAppScreen$lambda$135$lambda$134(showDictionaryBrowser$delegate);
                        }
                    };
                    composer.updateRememberedValue(obj14);
                    objRememberedValue = obj14;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                DictionaryBrowserDialog(ttsHelper2, (Function0) objRememberedValue, $composer2, ($dirty & 14) | 48);
            } else {
                $composer2.startReplaceGroup(-161769690);
            }
            $composer2.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ttsHelper3 = ttsHelper2;
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj15, Object obj16) {
                    return MainActivityKt.AcademyAppScreen$lambda$136(ttsHelper3, $changed, i, (Composer) obj15, ((Integer) obj16).intValue());
                }
            });
        }
    }

    private static final boolean AcademyAppScreen$lambda$3(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void AcademyAppScreen$lambda$4(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean AcademyAppScreen$lambda$6(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void AcademyAppScreen$lambda$7(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String AcademyAppScreen$lambda$10(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<String> AcademyAppScreen$lambda$13(MutableState<List<String>> mutableState) {
        return mutableState.getValue();
    }

    static final Unit AcademyAppScreen$lambda$17$lambda$16(MutableState $isAdminLoggedIn$delegate, MutableState $loggedInEmail$delegate, MutableState $isLoggedIn$delegate, boolean isAdmin, String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        AcademyAppScreen$lambda$7($isAdminLoggedIn$delegate, isAdmin);
        $loggedInEmail$delegate.setValue(email);
        AcademyAppScreen$lambda$4($isLoggedIn$delegate, true);
        return Unit.INSTANCE;
    }

    private static final TeacherProfile AcademyAppScreen$lambda$19(State<TeacherProfile> state) {
        return (TeacherProfile) state.getValue();
    }

    private static final List<CourseInfo> AcademyAppScreen$lambda$20(State<? extends List<CourseInfo>> state) {
        return (List) state.getValue();
    }

    private static final List<BatchSchedule> AcademyAppScreen$lambda$21(State<? extends List<BatchSchedule>> state) {
        return (List) state.getValue();
    }

    private static final ContactInfo AcademyAppScreen$lambda$22(State<ContactInfo> state) {
        return (ContactInfo) state.getValue();
    }

    private static final boolean AcademyAppScreen$lambda$23(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final String AcademyAppScreen$lambda$25(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean AcademyAppScreen$lambda$28(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void AcademyAppScreen$lambda$29(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String AcademyAppScreen$lambda$31(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean AcademyAppScreen$lambda$34(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void AcademyAppScreen$lambda$35(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean AcademyAppScreen$lambda$37(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void AcademyAppScreen$lambda$38(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final Triple<String, String, String> AcademyAppScreen$lambda$41(MutableState<Triple<String, String, String>> mutableState) {
        return mutableState.getValue();
    }

    static final Unit AcademyAppScreen$lambda$63(ScrollState $mainScrollState, final State $teacherProfile$delegate, final State $contactInfo$delegate, final Context $context, final MutableState $isAdminLoggedIn$delegate, final MutableState $showAdminDashboardDialog$delegate, final MutableState $loggedInEmail$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C308@14360L11,307@14292L112,224@10283L709,238@11020L3227,222@10178L4240:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1150007616, $changed, -1, "com.example.sushikhasacademyapp.AcademyAppScreen.<anonymous> (MainActivity.kt:222)");
            }
            AppBarKt.m2090TopAppBarGHTll3U(ComposableLambdaKt.rememberComposableLambda(2097849980, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda154
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.AcademyAppScreen$lambda$63$lambda$46($teacherProfile$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), MotionUtilsKt.m8744elevateOnScrollwH6b6FI$default(Modifier.INSTANCE, $mainScrollState, 0.0f, 2, null), null, ComposableLambdaKt.rememberComposableLambda(-1162254991, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda155
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MainActivityKt.AcademyAppScreen$lambda$63$lambda$62($contactInfo$delegate, $context, $isAdminLoggedIn$delegate, $showAdminDashboardDialog$delegate, $loggedInEmail$delegate, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), 0.0f, null, TopAppBarDefaults.INSTANCE.m3287topAppBarColors5tl4gsc(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurfaceVariant(), 0L, 0L, 0L, 0L, 0L, $composer, TopAppBarDefaults.$stable << 18, 62), null, $composer, 3078, RotationOptions.ROTATE_180);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AcademyAppScreen$lambda$63$lambda$46(State $teacherProfile$delegate, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C225@10305L669:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2097849980, $changed, -1, "com.example.sushikhasacademyapp.AcademyAppScreen.<anonymous>.<anonymous> (MainActivity.kt:225)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 2104393111, "C228@10458L10,229@10567L11,226@10338L285,233@10835L10,234@10907L11,231@10648L304:MainActivity.kt#t7vdsr");
            TextKt.m3072TextNvy7gAk("Sushiksha English Academy", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimaryFixed(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 6, 0, 131066);
            TextKt.m3072TextNvy7gAk("Guided by " + (!StringsKt.isBlank(AcademyAppScreen$lambda$19($teacherProfile$delegate).getName()) ? AcademyAppScreen$lambda$19($teacherProfile$delegate).getName() : "Faculty Mentor"), null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131066);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit AcademyAppScreen$lambda$63$lambda$62(final State $contactInfo$delegate, final Context $context, final MutableState $isAdminLoggedIn$delegate, final MutableState $showAdminDashboardDialog$delegate, final MutableState $loggedInEmail$delegate, RowScope TopAppBar, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TopAppBar, "$this$TopAppBar");
        ComposerKt.sourceInformation($composer, "C239@11063L468,239@11042L768,269@12440L70,271@12543L108,271@12532L119,275@12694L325,281@13021L1208,275@12673L1556:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1162254991, $changed, -1, "com.example.sushikhasacademyapp.AcademyAppScreen.<anonymous>.<anonymous> (MainActivity.kt:239)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -235547547, "CC(remember):MainActivity.kt#9igjgp");
            boolean zChanged = $composer.changed($contactInfo$delegate) | $composer.changedInstance($context);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda99
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivityKt.AcademyAppScreen$lambda$63$lambda$62$lambda$49$lambda$48($context, $contactInfo$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m8689getLambda$224059377$app(), $composer, 1572864, 62);
            if (AcademyAppScreen$lambda$6($isAdminLoggedIn$delegate)) {
                $composer.startReplaceGroup(1288829226);
                ComposerKt.sourceInformation($composer, "258@11986L87,258@11965L407");
                ComposerKt.sourceInformationMarkerStart($composer, -235518392, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda100
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.AcademyAppScreen$lambda$63$lambda$62$lambda$51$lambda$50($showAdminDashboardDialog$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue2 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                IconButtonKt.IconButton((Function0) objRememberedValue2, null, false, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1121451946$app(), $composer, 1572870, 62);
            } else {
                $composer.startReplaceGroup(1276970641);
            }
            $composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerStart($composer, -235503881, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue3 = $composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ProfilePrefs.INSTANCE.getProfileImagePath($context), null, 2, null);
                $composer.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue3 = objMutableStateOf$default;
            }
            final MutableState savedProfilePath$delegate = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -235500547, "CC(remember):MainActivity.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($context);
            Object objRememberedValue4 = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object obj3 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda101
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivityKt.AcademyAppScreen$lambda$63$lambda$62$lambda$56$lambda$55($context, savedProfilePath$delegate);
                    }
                };
                $composer.updateRememberedValue(obj3);
                objRememberedValue4 = obj3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            EffectsKt.SideEffect((Function0) objRememberedValue4, $composer, 0);
            ComposerKt.sourceInformationMarkerStart($composer, -235495498, "CC(remember):MainActivity.kt#9igjgp");
            boolean zChangedInstance2 = $composer.changedInstance($context);
            Object objRememberedValue5 = $composer.rememberedValue();
            if (zChangedInstance2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object obj4 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda102
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivityKt.AcademyAppScreen$lambda$63$lambda$62$lambda$59$lambda$58($context, $loggedInEmail$delegate, $isAdminLoggedIn$delegate);
                    }
                };
                $composer.updateRememberedValue(obj4);
                objRememberedValue5 = obj4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            IconButtonKt.IconButton((Function0) objRememberedValue5, null, false, null, null, null, ComposableLambdaKt.rememberComposableLambda(-333679482, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda103
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj5, Object obj6) {
                    return MainActivityKt.AcademyAppScreen$lambda$63$lambda$62$lambda$61(savedProfilePath$delegate, (Composer) obj5, ((Integer) obj6).intValue());
                }
            }, $composer, 54), $composer, 1572864, 62);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AcademyAppScreen$lambda$63$lambda$62$lambda$49$lambda$48(Context $context, State $contactInfo$delegate) {
        String phone = AcademyAppScreen$lambda$22($contactInfo$delegate).getPhone();
        if (StringsKt.isBlank(phone)) {
            phone = "+919836926092";
        }
        String phoneNum = phone;
        try {
            Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phoneNum));
            $context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText($context, "Calling: " + phoneNum, 0).show();
        }
        return Unit.INSTANCE;
    }

    static final Unit AcademyAppScreen$lambda$63$lambda$62$lambda$51$lambda$50(MutableState $showAdminDashboardDialog$delegate) {
        AcademyAppScreen$lambda$35($showAdminDashboardDialog$delegate, true);
        return Unit.INSTANCE;
    }

    private static final String AcademyAppScreen$lambda$63$lambda$62$lambda$53(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    static final Unit AcademyAppScreen$lambda$63$lambda$62$lambda$56$lambda$55(Context $context, MutableState $savedProfilePath$delegate) {
        $savedProfilePath$delegate.setValue(ProfilePrefs.INSTANCE.getProfileImagePath($context));
        return Unit.INSTANCE;
    }

    static final Unit AcademyAppScreen$lambda$63$lambda$62$lambda$59$lambda$58(Context $context, MutableState $loggedInEmail$delegate, MutableState $isAdminLoggedIn$delegate) {
        Intent intent = new Intent($context, (Class<?>) ProfileActivity.class);
        intent.putExtra("USER_EMAIL", AcademyAppScreen$lambda$10($loggedInEmail$delegate));
        intent.putExtra("IS_ADMIN", AcademyAppScreen$lambda$6($isAdminLoggedIn$delegate));
        $context.startActivity(intent);
        return Unit.INSTANCE;
    }

    static final Unit AcademyAppScreen$lambda$63$lambda$62$lambda$61(MutableState $savedProfilePath$delegate, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-333679482, $changed, -1, "com.example.sushikhasacademyapp.AcademyAppScreen.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:282)");
            }
            String path = AcademyAppScreen$lambda$63$lambda$62$lambda$53($savedProfilePath$delegate);
            String str = path;
            if (!(str == null || StringsKt.isBlank(str))) {
                $composer.startReplaceGroup(-2090997379);
                ComposerKt.sourceInformation($composer, "284@13156L711");
                Modifier modifierM265backgroundbw27NRU$default = BackgroundKt.m265backgroundbw27NRU$default(ClipKt.clip(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(34)), RoundedCornerShapeKt.getCircleShape()), Color.INSTANCE.m5129getLightGray0d7_KjU(), null, 2, null);
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM265backgroundbw27NRU$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i3 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1124689786, "C291@13515L322:MainActivity.kt#t7vdsr");
                SingletonAsyncImageKt.m8524AsyncImagegl8XCv8(new File(path), "User Profile", SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, false, null, $composer, 1573296, 0, 4024);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-2090244792);
                ComposerKt.sourceInformation($composer, "302@14132L11,299@13929L252");
                IconKt.m2520Iconww6aTOc(AccountCircleKt.getAccountCircle(Icons.INSTANCE.getDefault()), "User Profile", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit AcademyAppScreen$lambda$67(ScrollState $mainScrollState, final MutableState $selectedCourseForInquiry$delegate, final MutableState $showInquiryDialog$delegate, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C313@14475L782:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1585527997, $changed, -1, "com.example.sushikhasacademyapp.AcademyAppScreen.<anonymous> (MainActivity.kt:313)");
            }
            Alignment.Horizontal end = Alignment.INSTANCE.getEnd();
            Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(10));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, end, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((432 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 383819945, "C317@14634L45,326@15158L11,319@14756L141,318@14696L547:MainActivity.kt#t7vdsr");
            MotionUtilsKt.ScrollToTopFAB($mainScrollState, (Modifier) null, 0, (CoroutineScope) null, $composer, 0, 14);
            Modifier modifierBounceClick$default = MotionUtilsKt.bounceClick$default(Modifier.INSTANCE, 0.0f, null, 3, null);
            long jM2238getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
            long jM5134getWhite0d7_KjU = Color.INSTANCE.m5134getWhite0d7_KjU();
            Function2<Composer, Integer, Unit> function2M8690getLambda$294223155$app = ComposableSingletons$MainActivityKt.INSTANCE.m8690getLambda$294223155$app();
            Function2<Composer, Integer, Unit> lambda$1869079054$app = ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1869079054$app();
            ComposerKt.sourceInformationMarkerStart($composer, 1674952710, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda57
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivityKt.AcademyAppScreen$lambda$67$lambda$66$lambda$65$lambda$64($selectedCourseForInquiry$delegate, $showInquiryDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd(
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x01a8: INVOKE (r2v11 ?? I:??[OBJECT, ARRAY]) STATIC call: androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(androidx.compose.runtime.Composer):void A[MD:(androidx.compose.runtime.Composer):void (m)] in method: com.example.sushikhasacademyapp.MainActivityKt.AcademyAppScreen$lambda$67(androidx.compose.foundation.ScrollState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int):kotlin.Unit, file: classes7.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r2v11 ??
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                */
            /*
                Method dump skipped, instruction units count: 489
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.sushikhasacademyapp.MainActivityKt.AcademyAppScreen$lambda$67(androidx.compose.foundation.ScrollState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int):kotlin.Unit");
        }

        static final Unit AcademyAppScreen$lambda$67$lambda$66$lambda$65$lambda$64(MutableState $selectedCourseForInquiry$delegate, MutableState $showInquiryDialog$delegate) {
            $selectedCourseForInquiry$delegate.setValue("General Inquiry");
            AcademyAppScreen$lambda$29($showInquiryDialog$delegate, true);
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$127(ScrollState $mainScrollState, final MutableState $latestBatchNotice$delegate, final Context $context, final MutableState $isAdminLoggedIn$delegate, final CoroutineScope $coroutineScope, final SqlConnectRepository $repository, State $teacherProfile$delegate, final MutableState $selectedCourseForInquiry$delegate, final MutableState $showInquiryDialog$delegate, TextToSpeechHelper $ttsHelper, final MutableState $showDictionaryBrowser$delegate, MutableState $selectedCategory$delegate, final State $courses$delegate, final State $isLoading$delegate, final State $batches$delegate, final LazyListState $batchesLazyListState, final MutableState $enrolledBatchesState$delegate, final LazyListState $testimonialsLazyListState, final List $testimonials, State $contactInfo$delegate, PaddingValues innerPadding, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Function0<ComposeUiNode> function2;
            Function0<ComposeUiNode> function3;
            final MutableState mutableState;
            Function0<ComposeUiNode> function4;
            String str;
            Function0<ComposeUiNode> function5;
            Function0<ComposeUiNode> function6;
            Function0<ComposeUiNode> function7;
            Composer composer;
            Function0<ComposeUiNode> function8;
            Intrinsics.checkNotNullParameter(innerPadding, "innerPadding");
            ComposerKt.sourceInformation($composer, "CN(innerPadding)332@15300L16377:MainActivity.kt#t7vdsr");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer.changed(innerPadding) ? 4 : 2;
            }
            int $dirty2 = $dirty;
            if ($composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1431090187, $dirty2, -1, "com.example.sushikhasacademyapp.AcademyAppScreen.<anonymous> (MainActivity.kt:332)");
                }
                Modifier modifierM823paddingqDBjuR0$default = PaddingKt.m823paddingqDBjuR0$default(ScrollKt.verticalScroll$default(PaddingKt.padding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), innerPadding), $mainScrollState, false, null, false, 14, null), 0.0f, 0.0f, 0.0f, Dp.m7905constructorimpl(80), 7, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM823paddingqDBjuR0$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                int i3 = ((0 >> 6) & 112) | 6;
                ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 995298847, "C339@15524L54,393@18515L51,394@18616L334,403@19000L338,417@19519L2079,414@19402L2196,465@21658L376,475@22048L41,478@22171L252,485@22437L41,488@22529L255,495@22798L41,498@22884L150,505@23146L122,509@23336L2329,503@23048L2617,554@25679L41,557@25765L150,624@29837L41,627@29926L205,631@30145L41,634@30244L154,639@30412L856,657@31282L41,660@31375L292:MainActivity.kt#t7vdsr");
                MotionUtilsKt.m8739ScrollProgressIndicatorpc5RIQQ($mainScrollState, null, 0L, 0L, 0.0f, $composer, 0, 30);
                Triple<String, String, String> tripleAcademyAppScreen$lambda$41 = AcademyAppScreen$lambda$41($latestBatchNotice$delegate);
                if (tripleAcademyAppScreen$lambda$41 == null) {
                    $composer.startReplaceGroup(994973531);
                    $composer.endReplaceGroup();
                } else {
                    $composer.startReplaceGroup(994973532);
                    ComposerKt.sourceInformation($composer, "*344@15761L7,345@15827L7,346@15865L2597,342@15659L2803");
                    final String strComponent1 = tripleAcademyAppScreen$lambda$41.component1();
                    final String strComponent2 = tripleAcademyAppScreen$lambda$41.component2();
                    tripleAcademyAppScreen$lambda$41.component3();
                    ComposerKt.sourceInformationMarkerStart($composer, -1662348691, "CC(remember):MainActivity.kt#9igjgp");
                    Object objRememberedValue = $composer.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda18
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return Integer.valueOf(MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$83$lambda$69$lambda$68(((Integer) obj2).intValue()));
                            }
                        };
                        $composer.updateRememberedValue(obj);
                        objRememberedValue = obj;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    EnterTransition enterTransitionPlus = EnterExitTransitionKt.slideInVertically$default(null, (Function1) objRememberedValue, 1, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
                    ComposerKt.sourceInformationMarkerStart($composer, -1662346579, "CC(remember):MainActivity.kt#9igjgp");
                    Object objRememberedValue2 = $composer.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        Object obj2 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda20
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj3) {
                                return Integer.valueOf(MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$83$lambda$71$lambda$70(((Integer) obj3).intValue()));
                            }
                        };
                        $composer.updateRememberedValue(obj2);
                        objRememberedValue2 = obj2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    AnimatedVisibilityKt.AnimatedVisibility(columnScope, true, (Modifier) null, enterTransitionPlus, EnterExitTransitionKt.slideOutVertically$default(null, (Function1) objRememberedValue2, 1, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)), (String) null, (Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1199453570, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda21
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj3, Object obj4, Object obj5) {
                            return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$83$lambda$82(strComponent1, strComponent2, $context, $latestBatchNotice$delegate, $isAdminLoggedIn$delegate, (AnimatedVisibilityScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, $composer, 54), $composer, (i3 & 14) | 1600560, 18);
                    Unit unit = Unit.INSTANCE;
                    $composer.endReplaceGroup();
                    Unit unit2 = Unit.INSTANCE;
                }
                InfiniteTransition infiniteTransitionRememberInfiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition("LiveSyncPulse", $composer, 6, 0);
                final State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.5f, 1.0f, AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(900, 0, EasingKt.getLinearOutSlowInEasing(), 2, null), RepeatMode.Reverse, 0L, 4, null), "pulseAlpha", $composer, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
                final State<Float> stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.85f, 1.15f, AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(900, 0, EasingKt.getLinearOutSlowInEasing(), 2, null), RepeatMode.Reverse, 0L, 4, null), "pulseScale", $composer, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
                SurfaceKt.m2929SurfaceT9BRK9s(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), null, ColorKt.Color(4292672743L), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1554975578, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$92($coroutineScope, $repository, $context, stateAnimateFloat2, stateAnimateFloat, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, $composer, 54), $composer, 12583302, 122);
                Modifier modifierParallaxScroll = MotionUtilsKt.parallaxScroll(Modifier.INSTANCE, $mainScrollState, 0.25f);
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierParallaxScroll);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor2;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i6 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -86543005, "C468@21857L145,466@21747L273:MainActivity.kt#t7vdsr");
                TeacherProfile teacherProfileAcademyAppScreen$lambda$19 = AcademyAppScreen$lambda$19($teacherProfile$delegate);
                ComposerKt.sourceInformationMarkerStart($composer, 2075421664, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue3 = $composer.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda23
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$95$lambda$94$lambda$93($selectedCourseForInquiry$delegate, $showInquiryDialog$delegate);
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                TeacherProfileCard(teacherProfileAcademyAppScreen$lambda$19, (Function0) objRememberedValue3, $composer, TeacherProfile.$stable | 48);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), $composer, 6);
                Modifier modifierScrollEntranceEffect$default = MotionUtilsKt.scrollEntranceEffect$default(Modifier.INSTANCE, $mainScrollState, 0.0f, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierScrollEntranceEffect$default);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor3;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i9 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -146680882, "C481@22359L32,479@22252L157:MainActivity.kt#t7vdsr");
                ComposerKt.sourceInformationMarkerStart($composer, -1667296328, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue4 = $composer.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda24
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$98$lambda$97$lambda$96($showDictionaryBrowser$delegate);
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                WordOfTheDayCard($ttsHelper, (Function0) objRememberedValue4, $composer, 48);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(20)), $composer, 6);
                Modifier modifierScrollEntranceEffect$default2 = MotionUtilsKt.scrollEntranceEffect$default(Modifier.INSTANCE, $mainScrollState, 0.0f, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifierScrollEntranceEffect$default2);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i10 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function3 = constructor4;
                    $composer.createNode(function3);
                } else {
                    function3 = constructor4;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl4 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i11 = (i10 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i12 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -23776852, "C491@22727L25,489@22610L160:MainActivity.kt#t7vdsr");
                String strAcademyAppScreen$lambda$25 = AcademyAppScreen$lambda$25($selectedCategory$delegate);
                ComposerKt.sourceInformationMarkerStart($composer, -1801878704, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue5 = $composer.rememberedValue();
                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    mutableState = $selectedCategory$delegate;
                    objRememberedValue5 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda25
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$101$lambda$100$lambda$99(mutableState, (String) obj3);
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue5);
                } else {
                    mutableState = $selectedCategory$delegate;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                CategoryFilterBar(strAcademyAppScreen$lambda$25, (Function1) objRememberedValue5, $composer, 48);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), $composer, 6);
                SectionHeader("Popular Courses", "Choose a program tailored for your learning goals", $composer, 54);
                String strAcademyAppScreen$lambda$26 = AcademyAppScreen$lambda$25(mutableState);
                ComposerKt.sourceInformationMarkerStart($composer, 1833449615, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue6 = $composer.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    Object obj3 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda26
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$104$lambda$103((AnimatedContentTransitionScope) obj4);
                        }
                    };
                    $composer.updateRememberedValue(obj3);
                    objRememberedValue6 = obj3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                AnimatedContentKt.AnimatedContent(strAcademyAppScreen$lambda$26, null, (Function1) objRememberedValue6, null, "CategoryCoursesTransition", null, ComposableLambdaKt.rememberComposableLambda(-1686136213, true, new Function4() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda27
                    @Override // kotlin.jvm.functions.Function4
                    public final Object invoke(Object obj4, Object obj5, Object obj6, Object obj7) {
                        return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$113($courses$delegate, $isLoading$delegate, $selectedCourseForInquiry$delegate, $showInquiryDialog$delegate, (AnimatedContentScope) obj4, (String) obj5, (Composer) obj6, ((Integer) obj7).intValue());
                    }
                }, $composer, 54), $composer, 1597824, 42);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(20)), $composer, 6);
                SectionHeader("Upcoming Batches", "Limited seats available for individual attention", $composer, 54);
                if (AcademyAppScreen$lambda$21($batches$delegate).isEmpty()) {
                    $composer.startReplaceGroup(1005171974);
                    ComposerKt.sourceInformation($composer, "563@25970L903");
                    Modifier modifierM820paddingVpY3zN4 = PaddingKt.m820paddingVpY3zN4(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(16), Dp.m7905constructorimpl(12));
                    Alignment centerStart = Alignment.INSTANCE.getCenterStart();
                    ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(centerStart, false);
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap5 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier($composer, modifierM820paddingVpY3zN4);
                    Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                    int i13 = ((((54 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function7 = constructor5;
                        $composer.createNode(function7);
                    } else {
                        function7 = constructor5;
                        $composer.useNode();
                    }
                    Composer composerM4367constructorimpl5 = Updater.m4367constructorimpl($composer);
                    Updater.m4375setimpl(composerM4367constructorimpl5, measurePolicyMaybeCachedBoxMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                    int i14 = (i13 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                    int i15 = ((54 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -1485867172, "C:MainActivity.kt#t7vdsr");
                    if (AcademyAppScreen$lambda$23($isLoading$delegate)) {
                        $composer.startReplaceGroup(-1485858710);
                        ComposerKt.sourceInformation($composer, "570@26269L372");
                        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                        Modifier modifier = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                        CompositionLocalMap currentCompositionLocalMap6 = $composer.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier($composer, modifier);
                        Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                        int i16 = ((((384 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function8 = constructor6;
                            $composer.createNode(function8);
                        } else {
                            function8 = constructor6;
                            $composer.useNode();
                        }
                        Composer composerM4367constructorimpl6 = Updater.m4367constructorimpl($composer);
                        Updater.m4375setimpl(composerM4367constructorimpl6, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl(composerM4367constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4371initimpl(composerM4367constructorimpl6, Integer.valueOf(iHashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4373reconcileimpl(composerM4367constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4375setimpl(composerM4367constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                        int i17 = (i16 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        int i18 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -1499509018, "C571@26351L78,572@26458L39,573@26594L10,573@26526L89:MainActivity.kt#t7vdsr");
                        ProgressIndicatorKt.m2727CircularProgressIndicator4lLiAd8(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(18)), 0L, Dp.m7905constructorimpl(2), 0L, 0, 0.0f, $composer, 390, 58);
                        SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
                        TextKt.m3072TextNvy7gAk("Fetching batches from SQL database...", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 131070);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endReplaceGroup();
                        composer = $composer;
                    } else {
                        $composer.startReplaceGroup(-1485443372);
                        ComposerKt.sourceInformation($composer, "576@26760L10,576@26804L11,576@26695L138");
                        TextKt.m3072TextNvy7gAk("No upcoming batches scheduled yet.", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 131066);
                        composer = $composer;
                        composer.endReplaceGroup();
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                    str = "CC(remember):MainActivity.kt#9igjgp";
                } else {
                    $composer.startReplaceGroup(1006167291);
                    ComposerKt.sourceInformation($composer, "580@26911L2898");
                    ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier2 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap7 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier($composer, modifier2);
                    Function0<ComposeUiNode> constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                    int i19 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function4 = constructor7;
                        $composer.createNode(function4);
                    } else {
                        function4 = constructor7;
                        $composer.useNode();
                    }
                    Composer composerM4367constructorimpl7 = Updater.m4367constructorimpl($composer);
                    Updater.m4375setimpl(composerM4367constructorimpl7, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl7, Integer.valueOf(iHashCode7), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl7, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl7, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                    int i20 = (i19 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    int i21 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -442325723, "C581@26940L88,582@27049L40,587@27347L2444,583@27110L2681:MainActivity.kt#t7vdsr");
                    MotionUtilsKt.m8740ScrollProgressIndicatorq9LK7_k($batchesLazyListState, AcademyAppScreen$lambda$21($batches$delegate).size(), null, 0L, 0.0f, $composer, 0, 28);
                    SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(4)), $composer, 6);
                    PaddingValues paddingValuesM814PaddingValuesYgX7TsA$default = PaddingKt.m814PaddingValuesYgX7TsA$default(Dp.m7905constructorimpl(16), 0.0f, 2, null);
                    Arrangement.HorizontalOrVertical horizontalOrVerticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart($composer, 1925406695, "CC(remember):MainActivity.kt#9igjgp");
                    boolean zChanged = $composer.changed($batches$delegate) | $composer.changed($batchesLazyListState) | $composer.changedInstance($context);
                    Object objRememberedValue7 = $composer.rememberedValue();
                    if (zChanged) {
                        str = "CC(remember):MainActivity.kt#9igjgp";
                    } else {
                        str = "CC(remember):MainActivity.kt#9igjgp";
                        if (objRememberedValue7 != Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        LazyDslKt.LazyRow(null, $batchesLazyListState, paddingValuesM814PaddingValuesYgX7TsA$default, false, horizontalOrVerticalM689spacedBy0680j_4, null, null, false, null, (Function1) objRememberedValue7, $composer, 24960, 489);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endReplaceGroup();
                    }
                    Object obj4 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda28
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj5) {
                            return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$119$lambda$118$lambda$117($batches$delegate, $batchesLazyListState, $context, $enrolledBatchesState$delegate, $isAdminLoggedIn$delegate, (LazyListScope) obj5);
                        }
                    };
                    $composer.updateRememberedValue(obj4);
                    objRememberedValue7 = obj4;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    LazyDslKt.LazyRow(null, $batchesLazyListState, paddingValuesM814PaddingValuesYgX7TsA$default, false, horizontalOrVerticalM689spacedBy0680j_4, null, null, false, null, (Function1) objRememberedValue7, $composer, 24960, 489);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                }
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(24)), $composer, 6);
                Modifier modifierScrollEntranceEffect$default3 = MotionUtilsKt.scrollEntranceEffect$default(Modifier.INSTANCE, $mainScrollState, 0.0f, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap8 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier($composer, modifierScrollEntranceEffect$default3);
                Function0<ComposeUiNode> constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                int i22 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function5 = constructor8;
                    $composer.createNode(function5);
                } else {
                    function5 = constructor8;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl8 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl8, measurePolicyMaybeCachedBoxMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl8, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl8, Integer.valueOf(iHashCode8), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl8, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl8, modifierMaterializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
                int i23 = (i22 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                int i24 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 99125535, "C628@30007L110:MainActivity.kt#t7vdsr");
                WhyChooseUsSection(!StringsKt.isBlank(AcademyAppScreen$lambda$19($teacherProfile$delegate).getName()) ? AcademyAppScreen$lambda$19($teacherProfile$delegate).getName() : "Our Faculty", $composer, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(24)), $composer, 6);
                SectionHeader("What Our Students Say", "Feedback from learners in Konnagar, West Bengal", $composer, 54);
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier3 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode9 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap9 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier($composer, modifier3);
                Function0<ComposeUiNode> constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                int i25 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    $composer.createNode(constructor9);
                } else {
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl9 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl9, measurePolicyColumnMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl9, currentCompositionLocalMap9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl9, Integer.valueOf(iHashCode9), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl9, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl9, modifierMaterializeModifier9, ComposeUiNode.INSTANCE.getSetModifier());
                int i26 = (i25 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i27 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1250809779, "C640@30437L98,641@30552L40,646@30835L419,642@30609L645:MainActivity.kt#t7vdsr");
                MotionUtilsKt.m8740ScrollProgressIndicatorq9LK7_k($testimonialsLazyListState, $testimonials.size(), null, 0L, 0.0f, $composer, 0, 28);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(4)), $composer, 6);
                PaddingValues paddingValuesM814PaddingValuesYgX7TsA$default2 = PaddingKt.m814PaddingValuesYgX7TsA$default(Dp.m7905constructorimpl(16), 0.0f, 2, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM689spacedBy0680j_5 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart($composer, 1702929026, str);
                boolean zChangedInstance = $composer.changedInstance($testimonials) | $composer.changed($testimonialsLazyListState);
                Object objRememberedValue8 = $composer.rememberedValue();
                if (zChangedInstance || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    Object obj5 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda19
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj6) {
                            return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$124$lambda$123$lambda$122($testimonials, $testimonialsLazyListState, (LazyListScope) obj6);
                        }
                    };
                    $composer.updateRememberedValue(obj5);
                    objRememberedValue8 = obj5;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                LazyDslKt.LazyRow(null, $testimonialsLazyListState, paddingValuesM814PaddingValuesYgX7TsA$default2, false, horizontalOrVerticalM689spacedBy0680j_5, null, null, false, null, (Function1) objRememberedValue8, $composer, 24960, 489);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(24)), $composer, 6);
                Modifier modifierScrollEntranceEffect$default4 = MotionUtilsKt.scrollEntranceEffect$default(Modifier.INSTANCE, $mainScrollState, 0.0f, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode10 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap10 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier($composer, modifierScrollEntranceEffect$default4);
                Function0<ComposeUiNode> constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                int i28 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function6 = constructor10;
                    $composer.createNode(function6);
                } else {
                    function6 = constructor10;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl10 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl10, measurePolicyMaybeCachedBoxMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl10, currentCompositionLocalMap10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl10, Integer.valueOf(iHashCode10), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl10, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl10, modifierMaterializeModifier10, ComposeUiNode.INSTANCE.getSetModifier());
                int i29 = (i28 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                int i30 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 222032169, "C661@31456L197:MainActivity.kt#t7vdsr");
                ContactFooterCard(AcademyAppScreen$lambda$22($contactInfo$delegate), !StringsKt.isBlank(AcademyAppScreen$lambda$19($teacherProfile$delegate).getName()) ? AcademyAppScreen$lambda$19($teacherProfile$delegate).getName() : "Faculty Mentor", $composer, ContactInfo.$stable);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        static final int AcademyAppScreen$lambda$127$lambda$126$lambda$83$lambda$69$lambda$68(int it) {
            return -it;
        }

        static final int AcademyAppScreen$lambda$127$lambda$126$lambda$83$lambda$71$lambda$70(int it) {
            return -it;
        }

        static final Unit AcademyAppScreen$lambda$127$lambda$126$lambda$83$lambda$82(final String $bTitle, final String $nTitle, final Context $context, final MutableState $latestBatchNotice$delegate, final MutableState $isAdminLoggedIn$delegate, AnimatedVisibilityScope AnimatedVisibility, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
            ComposerKt.sourceInformation($composer, "C351@16080L2364,347@15887L2557:MainActivity.kt#t7vdsr");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1199453570, $changed, -1, "com.example.sushikhasacademyapp.AcademyAppScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:347)");
            }
            SurfaceKt.m2929SurfaceT9BRK9s(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), null, ColorKt.Color(4279522093L), Color.INSTANCE.m5134getWhite0d7_KjU(), 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-964919655, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda147
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$83$lambda$82$lambda$81($bTitle, $nTitle, $context, $latestBatchNotice$delegate, $isAdminLoggedIn$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 12586374, 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:48:0x063a  */
        /* JADX WARN: Code duplicated, block: B:49:0x0648  */
        /* JADX WARN: Code duplicated, block: B:52:0x06a0  */
        static final Unit AcademyAppScreen$lambda$127$lambda$126$lambda$83$lambda$82$lambda$81(final String $bTitle, String $nTitle, final Context $context, final MutableState $latestBatchNotice$delegate, final MutableState $isAdminLoggedIn$delegate, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Function0<ComposeUiNode> function2;
            Function0<ComposeUiNode> function3;
            Composer composer;
            Composer composer2;
            Object objRememberedValue;
            ComposerKt.sourceInformation($composer, "C352@16106L2316:MainActivity.kt#t7vdsr");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-964919655, $changed, -1, "com.example.sushikhasacademyapp.AcademyAppScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:352)");
                }
                Modifier modifierM820paddingVpY3zN4 = PaddingKt.m820paddingVpY3zN4(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(16), Dp.m7905constructorimpl(10));
                Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM820paddingVpY3zN4);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i3 = ((438 >> 6) & 112) | 6;
                RowScope rowScope = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 1216466818, "C359@16496L770,371@17296L1100:MainActivity.kt#t7vdsr");
                Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor2;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i6 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -522858430, "C363@16708L81,364@16822L40,365@16895L341:MainActivity.kt#t7vdsr");
                IconKt.m2520Iconww6aTOc(CampaignKt.getCampaign(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, ColorKt.Color(4283096704L), $composer, 3120, 4);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), $composer, 6);
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor3;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i9 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -753194750, "C366@16997L10,366@16940L142,367@17167L10,367@17119L83:MainActivity.kt#t7vdsr");
                TextKt.m3072TextNvy7gAk("📢 New Batch Announcement!", null, ColorKt.Color(4283096704L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 390, 0, 131066);
                TextKt.m3072TextNvy7gAk("[" + $bTitle + "] " + $nTitle, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 1, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 24576, 114686);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier2 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifier2);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i10 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function3 = constructor4;
                    $composer.createNode(function3);
                } else {
                    function3 = constructor4;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl4 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i11 = (i10 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                int i12 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1300895601, "C373@17440L484,372@17382L718,384@18154L28,384@18133L233:MainActivity.kt#t7vdsr");
                ComposerKt.sourceInformationMarkerStart($composer, -96581602, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance = $composer.changedInstance($context) | $composer.changed($bTitle);
                Object objRememberedValue2 = $composer.rememberedValue();
                if (!zChangedInstance) {
                    composer = $composer;
                    if (objRememberedValue2 != Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ButtonKt.TextButton((Function0) objRememberedValue2, null, false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1784078045$app(), composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_POSITION_TYPE);
                    composer2 = composer;
                    ComposerKt.sourceInformationMarkerStart(composer2, -96559210, "CC(remember):MainActivity.kt#9igjgp");
                    objRememberedValue = composer2.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda50
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$83$lambda$82$lambda$81$lambda$80$lambda$79$lambda$78$lambda$77($latestBatchNotice$delegate);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m8692getLambda$412150436$app(), composer, 1572870, 62);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composer = $composer;
                }
                objRememberedValue2 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda49
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$83$lambda$82$lambda$81$lambda$80$lambda$79$lambda$76$lambda$75($context, $latestBatchNotice$delegate, $bTitle, $isAdminLoggedIn$delegate);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue2);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ButtonKt.TextButton((Function0) objRememberedValue2, null, false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1784078045$app(), composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_POSITION_TYPE);
                composer2 = composer;
                ComposerKt.sourceInformationMarkerStart(composer2, -96559210, "CC(remember):MainActivity.kt#9igjgp");
                objRememberedValue = composer2.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda50
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$83$lambda$82$lambda$81$lambda$80$lambda$79$lambda$78$lambda$77($latestBatchNotice$delegate);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m8692getLambda$412150436$app(), composer, 1572870, 62);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$127$lambda$126$lambda$83$lambda$82$lambda$81$lambda$80$lambda$79$lambda$76$lambda$75(Context $context, MutableState $latestBatchNotice$delegate, String $bTitle, MutableState $isAdminLoggedIn$delegate) {
            $latestBatchNotice$delegate.setValue(null);
            Intent intent = new Intent($context, (Class<?>) BatchDetailActivity.class);
            intent.putExtra("BATCH_TITLE", $bTitle);
            intent.putExtra("IS_ADMIN", AcademyAppScreen$lambda$6($isAdminLoggedIn$delegate));
            $context.startActivity(intent);
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$127$lambda$126$lambda$83$lambda$82$lambda$81$lambda$80$lambda$79$lambda$78$lambda$77(MutableState $latestBatchNotice$delegate) {
            $latestBatchNotice$delegate.setValue(null);
            return Unit.INSTANCE;
        }

        private static final float AcademyAppScreen$lambda$127$lambda$126$lambda$84(State<Float> state) {
            return ((Number) state.getValue()).floatValue();
        }

        private static final float AcademyAppScreen$lambda$127$lambda$126$lambda$85(State<Float> state) {
            return ((Number) state.getValue()).floatValue();
        }

        static final Unit AcademyAppScreen$lambda$127$lambda$126$lambda$92(final CoroutineScope $coroutineScope, final SqlConnectRepository $repository, final Context $context, final State $pulseScale$delegate, final State $pulseAlpha$delegate, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            ComposerKt.sourceInformation($composer, "C418@19537L2047:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1554975578, $changed, -1, "com.example.sushikhasacademyapp.AcademyAppScreen.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:418)");
                }
                Modifier modifierM820paddingVpY3zN4 = PaddingKt.m820paddingVpY3zN4(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(16), Dp.m7905constructorimpl(6));
                Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM820paddingVpY3zN4);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i3 = ((438 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 117988901, "C425@19870L935,446@20873L294,445@20827L739:MainActivity.kt#t7vdsr");
                Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor2;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                int i6 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1246842498, "C429@20092L202,426@19948L486,437@20459L39,440@20646L10,438@20523L260:MainActivity.kt#t7vdsr");
                Modifier modifierM865size3ABfNKs = SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart($composer, -1287142740, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChanged = $composer.changed($pulseScale$delegate) | $composer.changed($pulseAlpha$delegate);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda150
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$92$lambda$91$lambda$88$lambda$87$lambda$86($pulseScale$delegate, $pulseAlpha$delegate, (GraphicsLayerScope) obj);
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                BoxKt.Box(BackgroundKt.m265backgroundbw27NRU$default(ClipKt.clip(GraphicsLayerModifierKt.graphicsLayer(modifierM865size3ABfNKs, (Function1) objRememberedValue), RoundedCornerShapeKt.getCircleShape()), ColorKt.Color(4279673674L), null, 2, null), $composer, 0);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), $composer, 6);
                TextKt.m3072TextNvy7gAk("Firestore • Live Sync Active", null, ColorKt.Color(4279599165L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 390, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, 558026116, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance = $composer.changedInstance($coroutineScope) | $composer.changedInstance($repository) | $composer.changedInstance($context);
                Object objRememberedValue2 = $composer.rememberedValue();
                if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda151
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$92$lambda$91$lambda$90$lambda$89($coroutineScope, $context, $repository);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue2 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                IconButtonKt.IconButton((Function0) objRememberedValue2, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(24)), false, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m8685getLambda$1853647012$app(), $composer, 1572912, 60);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$127$lambda$126$lambda$92$lambda$91$lambda$88$lambda$87$lambda$86(State $pulseScale$delegate, State $pulseAlpha$delegate, GraphicsLayerScope graphicsLayer) {
            Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
            graphicsLayer.setScaleX(AcademyAppScreen$lambda$127$lambda$126$lambda$85($pulseScale$delegate));
            graphicsLayer.setScaleY(AcademyAppScreen$lambda$127$lambda$126$lambda$85($pulseScale$delegate));
            graphicsLayer.setAlpha(AcademyAppScreen$lambda$127$lambda$126$lambda$84($pulseAlpha$delegate));
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$127$lambda$126$lambda$92$lambda$91$lambda$90$lambda$89(CoroutineScope $coroutineScope, Context $context, SqlConnectRepository $repository) {
            BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new MainActivityKt$AcademyAppScreen$7$1$2$1$2$1$1($repository, null), 3, null);
            Toast.makeText($context, "Refreshed live data from Firestore!", 0).show();
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$127$lambda$126$lambda$95$lambda$94$lambda$93(MutableState $selectedCourseForInquiry$delegate, MutableState $showInquiryDialog$delegate) {
            $selectedCourseForInquiry$delegate.setValue("Direct Consultation");
            AcademyAppScreen$lambda$29($showInquiryDialog$delegate, true);
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$127$lambda$126$lambda$98$lambda$97$lambda$96(MutableState $showDictionaryBrowser$delegate) {
            AcademyAppScreen$lambda$38($showDictionaryBrowser$delegate, true);
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$127$lambda$126$lambda$101$lambda$100$lambda$99(MutableState $selectedCategory$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $selectedCategory$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final ContentTransform AcademyAppScreen$lambda$127$lambda$126$lambda$104$lambda$103(AnimatedContentTransitionScope AnimatedContent) {
            Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
            return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(280, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideInVertically$default(null, new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda53
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$104$lambda$103$lambda$102(((Integer) obj).intValue()));
                }
            }, 1, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(RotationOptions.ROTATE_180, 0, null, 6, null), 0.0f, 2, null));
        }

        static final int AcademyAppScreen$lambda$127$lambda$126$lambda$104$lambda$103$lambda$102(int it) {
            return 30;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        static final Unit AcademyAppScreen$lambda$127$lambda$126$lambda$113(State $courses$delegate, State $isLoading$delegate, final MutableState $selectedCourseForInquiry$delegate, final MutableState $showInquiryDialog$delegate, AnimatedContentScope AnimatedContent, String category, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Composer composer;
            Function0<ComposeUiNode> function2;
            boolean zContains;
            Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
            Intrinsics.checkNotNullParameter(category, "category");
            ComposerKt.sourceInformation($composer, "CN(category):MainActivity.kt#t7vdsr");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1686136213, $changed, -1, "com.example.sushikhasacademyapp.AcademyAppScreen.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:510)");
            }
            Iterable iterableAcademyAppScreen$lambda$20 = AcademyAppScreen$lambda$20($courses$delegate);
            Collection arrayList = new ArrayList();
            for (Object obj : iterableAcademyAppScreen$lambda$20) {
                CourseInfo courseInfo = (CourseInfo) obj;
                switch (category.hashCode()) {
                    case 582933283:
                        zContains = !category.equals("Competitive Exams") || StringsKt.contains((CharSequence) courseInfo.getTitle(), (CharSequence) "Competitive", true) || StringsKt.contains((CharSequence) courseInfo.getTitle(), (CharSequence) "IELTS", true);
                        break;
                    case 746727493:
                        zContains = category.equals("Corporate") ? StringsKt.contains((CharSequence) courseInfo.getTitle(), (CharSequence) "Business", true) : true;
                        break;
                    case 1006683058:
                        zContains = category.equals("Spoken English") ? StringsKt.contains((CharSequence) courseInfo.getTitle(), (CharSequence) "Spoken", true) : true;
                        break;
                    case 1944911751:
                        zContains = category.equals("Grammar") ? StringsKt.contains((CharSequence) courseInfo.getTitle(), (CharSequence) "Grammar", true) : true;
                        break;
                    default:
                        zContains = true;
                        break;
                }
                if (zContains) {
                    arrayList.add(obj);
                }
            }
            List currentFiltered = (List) arrayList;
            if (currentFiltered.isEmpty()) {
                $composer.startReplaceGroup(-2040696703);
                ComposerKt.sourceInformation($composer, "521@24032L910");
                Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(24));
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((54 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i3 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 908323799, "C:MainActivity.kt#t7vdsr");
                if (AcademyAppScreen$lambda$23($isLoading$delegate)) {
                    $composer.startReplaceGroup(908332602);
                    ComposerKt.sourceInformation($composer, "528@24323L390");
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier);
                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i4 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function2 = constructor2;
                        $composer.createNode(function2);
                    } else {
                        function2 = constructor2;
                        $composer.useNode();
                    }
                    Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                    Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = (i4 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    int i6 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 1039041606, "C529@24409L78,530@24520L40,531@24661L10,531@24593L90:MainActivity.kt#t7vdsr");
                    ProgressIndicatorKt.m2727CircularProgressIndicator4lLiAd8(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(20)), 0L, Dp.m7905constructorimpl(2), 0L, 0, 0.0f, $composer, 390, 58);
                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), $composer, 6);
                    TextKt.m3072TextNvy7gAk("Fetching courses from SQL database...", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 6, 0, 131070);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                    composer = $composer;
                } else {
                    $composer.startReplaceGroup(908772585);
                    ComposerKt.sourceInformation($composer, "534@24872L10,534@24775L119");
                    TextKt.m3072TextNvy7gAk("No courses available. Admin can add courses from the Admin Portal.", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 6, 0, 131070);
                    composer = $composer;
                    composer.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-2039756566);
                ComposerKt.sourceInformation($composer, "538@24988L645");
                Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(2));
                Composer composer2 = $composer;
                ComposerKt.sourceInformationMarkerStart(composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier2 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer2, modifier2);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    function0 = constructor3;
                    composer2.createNode(function0);
                } else {
                    function0 = constructor3;
                    composer2.useNode();
                }
                Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl(composer2);
                Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                Composer composer3 = composer2;
                ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i9 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer3, 1522518646, "C:MainActivity.kt#t7vdsr");
                composer3.startReplaceGroup(464755997);
                ComposerKt.sourceInformation(composer3, "*540@25186L399,540@25149L436");
                List list = currentFiltered;
                int i10 = 0;
                int i11 = 0;
                for (Object obj2 : list) {
                    int i12 = i11 + 1;
                    if (i11 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    Iterable iterable = list;
                    final CourseInfo courseInfo2 = (CourseInfo) obj2;
                    MotionUtilsKt.StaggeredAnimatedItem(i11, null, 0, ComposableLambdaKt.rememberComposableLambda(400009807, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj3, Object obj4) {
                            return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$113$lambda$112$lambda$111$lambda$110(courseInfo2, $selectedCourseForInquiry$delegate, $showInquiryDialog$delegate, (Composer) obj3, ((Integer) obj4).intValue());
                        }
                    }, composer3, 54), composer3, 3072, 6);
                    i11 = i12;
                    list = iterable;
                    composer3 = composer3;
                    composer2 = composer2;
                    i10 = i10;
                }
                composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$127$lambda$126$lambda$113$lambda$112$lambda$111$lambda$110(final CourseInfo $course, final MutableState $selectedCourseForInquiry$delegate, final MutableState $showInquiryDialog$delegate, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C543@25337L184,541@25220L335:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(400009807, $changed, -1, "com.example.sushikhasacademyapp.AcademyAppScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:541)");
                }
                ComposerKt.sourceInformationMarkerStart($composer, -2137627097, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance = $composer.changedInstance($course);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.AcademyAppScreen$lambda$127$lambda$126$lambda$113$lambda$112$lambda$111$lambda$110$lambda$109$lambda$108($course, $selectedCourseForInquiry$delegate, $showInquiryDialog$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                CourseCard($course, (Function0) objRememberedValue, $composer, CourseInfo.$stable);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$127$lambda$126$lambda$113$lambda$112$lambda$111$lambda$110$lambda$109$lambda$108(CourseInfo $course, MutableState $selectedCourseForInquiry$delegate, MutableState $showInquiryDialog$delegate) {
            $selectedCourseForInquiry$delegate.setValue($course.getTitle());
            AcademyAppScreen$lambda$29($showInquiryDialog$delegate, true);
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$127$lambda$126$lambda$119$lambda$118$lambda$117(State $batches$delegate, final LazyListState $batchesLazyListState, final Context $context, final MutableState $enrolledBatchesState$delegate, final MutableState $isAdminLoggedIn$delegate, LazyListScope LazyRow) {
            Intrinsics.checkNotNullParameter(LazyRow, "$this$LazyRow");
            final List<BatchSchedule> listAcademyAppScreen$lambda$21 = AcademyAppScreen$lambda$21($batches$delegate);
            LazyRow.items(listAcademyAppScreen$lambda$21.size(), null, new Function1<Integer, Object>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$AcademyAppScreen$lambda$127$lambda$126$lambda$119$lambda$118$lambda$117$$inlined$itemsIndexed$default$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int index) {
                    listAcademyAppScreen$lambda$21.get(index);
                    return null;
                }
            }, ComposableLambdaKt.composableLambdaInstance(2039820996, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$AcademyAppScreen$lambda$127$lambda$126$lambda$119$lambda$118$lambda$117$$inlined$itemsIndexed$default$3
                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                    invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(LazyItemScope $this$items, final int it, Composer $composer, int $changed) {
                    ComposerKt.sourceInformation($composer, "CN(it)214@10668L26:LazyDsl.kt#428nma");
                    int $dirty = $changed;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer.changed($this$items) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty |= $composer.changed(it) ? 32 : 16;
                    }
                    if (!$composer.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                        $composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(2039820996, $dirty, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:214)");
                    }
                    final BatchSchedule batchSchedule = (BatchSchedule) listAcademyAppScreen$lambda$21.get(it);
                    $composer.startReplaceGroup(-1398448316);
                    ComposerKt.sourceInformation($composer, "CN(index,batch)*589@27478L2265,589@27441L2302:MainActivity.kt#t7vdsr");
                    final LazyListState lazyListState = $batchesLazyListState;
                    final Context context = $context;
                    final MutableState mutableState = $enrolledBatchesState$delegate;
                    final MutableState mutableState2 = $isAdminLoggedIn$delegate;
                    MotionUtilsKt.StaggeredAnimatedItem(it, null, 0, ComposableLambdaKt.rememberComposableLambda(-1784449491, true, new Function2<Composer, Integer, Unit>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$AcademyAppScreen$7$1$9$1$1$1$1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer2, int $changed2) {
                            Function0<ComposeUiNode> function0;
                            ComposerKt.sourceInformation($composer2, "C590@27512L2201:MainActivity.kt#t7vdsr");
                            if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                                $composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1784449491, $changed2, -1, "com.example.sushikhasacademyapp.AcademyAppScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:590)");
                            }
                            Modifier modifierLazyItemScrollAnimation = MotionUtilsKt.lazyItemScrollAnimation(Modifier.INSTANCE, lazyListState, it);
                            final BatchSchedule batchSchedule2 = batchSchedule;
                            final Context context2 = context;
                            final MutableState<List<String>> mutableState3 = mutableState;
                            final MutableState<Boolean> mutableState4 = mutableState2;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierLazyItemScrollAnimation);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                                function0 = constructor;
                                $composer2.createNode(function0);
                            } else {
                                function0 = constructor;
                                $composer2.useNode();
                            }
                            Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer2);
                            Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            int i2 = (i >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i3 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, 782877197, "C591@27653L2026,591@27628L2051:MainActivity.kt#t7vdsr");
                            ComposerKt.sourceInformationMarkerStart($composer2, -1083123779, "CC(remember):MainActivity.kt#9igjgp");
                            boolean zChangedInstance = $composer2.changedInstance(batchSchedule2) | $composer2.changedInstance(context2);
                            Object objRememberedValue = $composer2.rememberedValue();
                            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = (Function0) new Function0<Unit>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$AcademyAppScreen$7$1$9$1$1$1$1$1$1$1
                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX WARN: Code duplicated, block: B:27:0x0108 A[LOOP:0: B:8:0x0053->B:27:0x0108, LOOP_END] */
                                    /* JADX WARN: Code duplicated, block: B:36:0x0106 A[SYNTHETIC] */
                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        boolean isEnrolled;
                                        String batchTitle;
                                        String canonicalTarget;
                                        String batchTitle2;
                                        int i4;
                                        boolean z;
                                        String batchTitle3 = batchSchedule2.getCourseTitle();
                                        String lowerCase = StringsKt.trim((CharSequence) batchTitle3).toString().toLowerCase(Locale.ROOT);
                                        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                                        String str = null;
                                        int i5 = 2;
                                        String canonicalTarget2 = StringsKt.trim((CharSequence) StringsKt.substringBefore$default(lowerCase, "(", (String) null, 2, (Object) null)).toString();
                                        Iterable iterableAcademyAppScreen$lambda$13 = MainActivityKt.AcademyAppScreen$lambda$13(mutableState3);
                                        if (!(iterableAcademyAppScreen$lambda$13 instanceof Collection) || !((Collection) iterableAcademyAppScreen$lambda$13).isEmpty()) {
                                            Iterator it2 = iterableAcademyAppScreen$lambda$13.iterator();
                                            while (true) {
                                                if (!it2.hasNext()) {
                                                    isEnrolled = false;
                                                    break;
                                                }
                                                String str2 = (String) it2.next();
                                                String lowerCase2 = StringsKt.trim((CharSequence) str2).toString().toLowerCase(Locale.ROOT);
                                                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                                                String string = StringsKt.trim((CharSequence) StringsKt.substringBefore$default(lowerCase2, "(", str, i5, str)).toString();
                                                if (Intrinsics.areEqual(string, canonicalTarget2)) {
                                                    batchTitle = batchTitle3;
                                                    canonicalTarget = canonicalTarget2;
                                                    batchTitle2 = str;
                                                    i4 = i5;
                                                } else if (StringsKt.equals(StringsKt.trim((CharSequence) str2).toString(), StringsKt.trim((CharSequence) batchTitle3).toString(), true) || StringsKt.startsWith(StringsKt.trim((CharSequence) str2).toString(), batchTitle3 + " (", true)) {
                                                    batchTitle = batchTitle3;
                                                    canonicalTarget = canonicalTarget2;
                                                    batchTitle2 = null;
                                                    i4 = 2;
                                                } else {
                                                    String str3 = canonicalTarget2;
                                                    batchTitle = batchTitle3;
                                                    canonicalTarget = canonicalTarget2;
                                                    batchTitle2 = null;
                                                    i4 = 2;
                                                    if (!StringsKt.contains$default((CharSequence) string, (CharSequence) str3, false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) canonicalTarget, (CharSequence) string, false, 2, (Object) null)) {
                                                        z = false;
                                                    }
                                                    if (z) {
                                                        isEnrolled = true;
                                                        break;
                                                    }
                                                    str = batchTitle2;
                                                    i5 = i4;
                                                    batchTitle3 = batchTitle;
                                                    canonicalTarget2 = canonicalTarget;
                                                }
                                                z = true;
                                                if (z) {
                                                    isEnrolled = true;
                                                    break;
                                                }
                                                str = batchTitle2;
                                                i5 = i4;
                                                batchTitle3 = batchTitle;
                                                canonicalTarget2 = canonicalTarget;
                                            }
                                        } else {
                                            isEnrolled = false;
                                        }
                                        if (MainActivityKt.AcademyAppScreen$lambda$6(mutableState4) || isEnrolled) {
                                            Intent intent = new Intent(context2, (Class<?>) BatchDetailActivity.class);
                                            BatchSchedule batchSchedule3 = batchSchedule2;
                                            MutableState<Boolean> mutableState5 = mutableState4;
                                            intent.putExtra("BATCH_TITLE", batchSchedule3.getCourseTitle());
                                            intent.putExtra("BATCH_TIMING", batchSchedule3.getTiming() + " (" + batchSchedule3.getDays() + ")");
                                            intent.putExtra("IS_ADMIN", MainActivityKt.AcademyAppScreen$lambda$6(mutableState5));
                                            context2.startActivity(intent);
                                            return;
                                        }
                                        Toast.makeText(context2, "You are not enrolled in " + batchSchedule2.getCourseTitle() + " yet. Contact Admin to enroll!", 1).show();
                                    }
                                };
                                $composer2.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            MainActivityKt.BatchCard(batchSchedule2, (Function0) objRememberedValue, $composer2, BatchSchedule.$stable);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer, 54), $composer, (((($dirty & 14) | ($dirty & 112)) >> 3) & 14) | 3072, 6);
                    $composer.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }));
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$127$lambda$126$lambda$124$lambda$123$lambda$122(final List $testimonials, final LazyListState $testimonialsLazyListState, LazyListScope LazyRow) {
            Intrinsics.checkNotNullParameter(LazyRow, "$this$LazyRow");
            LazyRow.items($testimonials.size(), null, new Function1<Integer, Object>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$AcademyAppScreen$lambda$127$lambda$126$lambda$124$lambda$123$lambda$122$$inlined$itemsIndexed$default$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int index) {
                    $testimonials.get(index);
                    return null;
                }
            }, ComposableLambdaKt.composableLambdaInstance(2039820996, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$AcademyAppScreen$lambda$127$lambda$126$lambda$124$lambda$123$lambda$122$$inlined$itemsIndexed$default$3
                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                    invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(LazyItemScope $this$items, final int it, Composer $composer, int $changed) {
                    ComposerKt.sourceInformation($composer, "CN(it)214@10668L26:LazyDsl.kt#428nma");
                    int $dirty = $changed;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer.changed($this$items) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty |= $composer.changed(it) ? 32 : 16;
                    }
                    if (!$composer.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                        $composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(2039820996, $dirty, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:214)");
                    }
                    final Testimonial testimonial = (Testimonial) $testimonials.get(it);
                    $composer.startReplaceGroup(1522356786);
                    ComposerKt.sourceInformation($composer, "CN(index,testimonial)*648@30969L245,648@30932L282:MainActivity.kt#t7vdsr");
                    final LazyListState lazyListState = $testimonialsLazyListState;
                    MotionUtilsKt.StaggeredAnimatedItem(it, null, 0, ComposableLambdaKt.rememberComposableLambda(1879775651, true, new Function2<Composer, Integer, Unit>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$AcademyAppScreen$7$1$11$1$1$1$1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer2, int $changed2) {
                            Function0<ComposeUiNode> function0;
                            ComposerKt.sourceInformation($composer2, "C649@30999L189:MainActivity.kt#t7vdsr");
                            if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                                $composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1879775651, $changed2, -1, "com.example.sushikhasacademyapp.AcademyAppScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:649)");
                            }
                            Modifier modifierLazyItemScrollAnimation = MotionUtilsKt.lazyItemScrollAnimation(Modifier.INSTANCE, lazyListState, it);
                            Testimonial testimonial2 = testimonial;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierLazyItemScrollAnimation);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                                function0 = constructor;
                                $composer2.createNode(function0);
                            } else {
                                function0 = constructor;
                                $composer2.useNode();
                            }
                            Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer2);
                            Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            int i2 = (i >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i3 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, 798318736, "C650@31116L42:MainActivity.kt#t7vdsr");
                            MainActivityKt.TestimonialCard(testimonial2, $composer2, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer, 54), $composer, (((($dirty & 14) | ($dirty & 112)) >> 3) & 14) | 3072, 6);
                    $composer.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }));
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$129$lambda$128(MutableState $showInquiryDialog$delegate) {
            AcademyAppScreen$lambda$29($showInquiryDialog$delegate, false);
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$131$lambda$130(Context $context, MutableState $showInquiryDialog$delegate, State $teacherProfile$delegate, String name, String phone, String course) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(phone, "phone");
            Intrinsics.checkNotNullParameter(course, "course");
            AcademyAppScreen$lambda$29($showInquiryDialog$delegate, false);
            Toast.makeText($context, "Thank you " + name + "! " + AcademyAppScreen$lambda$19($teacherProfile$delegate).getName() + "'s team will contact you at " + phone + " regarding " + course + ".", 1).show();
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$133$lambda$132(MutableState $showAdminDashboardDialog$delegate) {
            AcademyAppScreen$lambda$35($showAdminDashboardDialog$delegate, false);
            return Unit.INSTANCE;
        }

        static final Unit AcademyAppScreen$lambda$135$lambda$134(MutableState $showDictionaryBrowser$delegate) {
            AcademyAppScreen$lambda$38($showDictionaryBrowser$delegate, false);
            return Unit.INSTANCE;
        }

        public static final void TeacherProfileCard(final TeacherProfile teacherProfile, final Function0<Unit> onInquireClick, Composer $composer, final int $changed) {
            Intrinsics.checkNotNullParameter(teacherProfile, "teacherProfile");
            Intrinsics.checkNotNullParameter(onInquireClick, "onInquireClick");
            Composer $composer2 = $composer.startRestartGroup(548730721);
            ComposerKt.sourceInformation($composer2, "C(TeacherProfileCard)N(teacherProfile,onInquireClick)717@33142L11,716@33087L93,719@33215L38,721@33303L7797,712@32966L8134:MainActivity.kt#t7vdsr");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= ($changed & 8) == 0 ? $composer2.changed(teacherProfile) : $composer2.changedInstance(teacherProfile) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer2.changedInstance(onInquireClick) ? 32 : 16;
            }
            if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(548730721, $dirty, -1, "com.example.sushikhasacademyapp.TeacherProfileCard (MainActivity.kt:711)");
                }
                Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(16));
                CardColors cardColorsM2144cardColorsro_MJ88 = CardDefaults.INSTANCE.m2144cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getPrimaryContainer(), 0L, 0L, 0L, $composer2, CardDefaults.$stable << 12, 14);
                CardElevation cardElevationM2145cardElevationaqJV_2Y = CardDefaults.INSTANCE.m2145cardElevationaqJV_2Y(Dp.m7905constructorimpl(6), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer2, (CardDefaults.$stable << 18) | 6, 62);
                $composer2 = $composer2;
                CardKt.Card(modifierM819padding3ABfNKs, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(20)), cardColorsM2144cardColorsro_MJ88, cardElevationM2145cardElevationaqJV_2Y, null, ComposableLambdaKt.rememberComposableLambda(-1084285485, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda42
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return MainActivityKt.TeacherProfileCard$lambda$149(teacherProfile, onInquireClick, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer2, 54), $composer2, 196614, 16);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda43
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.TeacherProfileCard$lambda$150(teacherProfile, onInquireClick, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        static final Unit TeacherProfileCard$lambda$149(final TeacherProfile $teacherProfile, Function0 $onInquireClick, ColumnScope Card, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Function0<ComposeUiNode> function2;
            Function0<ComposeUiNode> function3;
            Function0<ComposeUiNode> function4;
            Function0<ComposeUiNode> function5;
            Function0<ComposeUiNode> function6;
            Intrinsics.checkNotNullParameter(Card, "$this$Card");
            ComposerKt.sourceInformation($composer, "C722@33313L7781:MainActivity.kt#t7vdsr");
            if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1084285485, $changed, -1, "com.example.sushikhasacademyapp.TeacherProfileCard.<anonymous> (MainActivity.kt:722)");
                }
                Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(20));
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((6 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i3 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -555027208, "C860@40494L41,868@40771L313,862@40549L535:MainActivity.kt#t7vdsr");
                if (StringsKt.isBlank($teacherProfile.getName())) {
                    $composer.startReplaceGroup(-555207722);
                    ComposerKt.sourceInformation($composer, "724@33423L739");
                    Modifier modifierM821paddingVpY3zN4$default = PaddingKt.m821paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m7905constructorimpl(16), 1, null);
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal center = Arrangement.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierM821paddingVpY3zN4$default);
                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i4 = ((((438 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function6 = constructor2;
                        $composer.createNode(function6);
                    } else {
                        function6 = constructor2;
                        $composer.useNode();
                    }
                    Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                    Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = (i4 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    int i6 = ((438 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 1468255742, "C731@33731L78,732@33830L40,735@34023L10,736@34092L11,733@33891L253:MainActivity.kt#t7vdsr");
                    ProgressIndicatorKt.m2727CircularProgressIndicator4lLiAd8(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(20)), 0L, Dp.m7905constructorimpl(2), 0L, 0, 0.0f, $composer, 390, 58);
                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), $composer, 6);
                    TextKt.m3072TextNvy7gAk("Fetching faculty profile from SQL database...", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimaryFixed(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 6, 0, 131066);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                } else {
                    $composer.startReplaceGroup(-554282341);
                    ComposerKt.sourceInformation($composer, "740@34200L5726");
                    Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifier);
                    Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int i7 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function1 = constructor3;
                        $composer.createNode(function1);
                    } else {
                        function1 = constructor3;
                        $composer.useNode();
                    }
                    Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl($composer);
                    Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i8 = (i7 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                    int i9 = ((384 >> 6) & 112) | 6;
                    Composer composer = $composer;
                    ComposerKt.sourceInformationMarkerStart(composer, 1738299291, "C774@35945L40,776@36007L3901:MainActivity.kt#t7vdsr");
                    if (StringsKt.isBlank($teacherProfile.getDpUrl())) {
                        composer.startReplaceGroup(1738614932);
                        ComposerKt.sourceInformation(composer, "759@35199L11,760@35278L11,752@34832L1069");
                        Modifier modifierBackground$default = BackgroundKt.background$default(ClipKt.clip(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(72)), RoundedCornerShapeKt.getCircleShape()), Brush.INSTANCE.m5057linearGradientmHitzGk((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m5087boximpl(MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getPrimary()), Color.m5087boximpl(MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getSecondary())}), (8 & 2) != 0 ? Offset.INSTANCE.m4868getZeroF1C5BW0() : 0L, (8 & 4) != 0 ? Offset.INSTANCE.m4866getInfiniteF1C5BW0() : 0L, (8 & 8) != 0 ? TileMode.INSTANCE.m5492getClamp3opZhB0() : 0), null, 0.0f, 6, null);
                        Alignment center2 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                        ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                        CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer, modifierBackground$default);
                        Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                        int i10 = ((((48 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer.startReusableNode();
                        if (composer.getInserting()) {
                            function2 = constructor4;
                            composer.createNode(function2);
                        } else {
                            function2 = constructor4;
                            composer.useNode();
                        }
                        Composer composerM4367constructorimpl4 = Updater.m4367constructorimpl(composer);
                        Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                        int i11 = (i10 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i12 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer, 1930999316, "C769@35785L10,766@35535L340:MainActivity.kt#t7vdsr");
                        Iterable iterableSplit$default = StringsKt.split$default((CharSequence) $teacherProfile.getName(), new String[]{" "}, false, 0, 6, (Object) null);
                        int i13 = 0;
                        Collection arrayList = new ArrayList();
                        Iterator it = iterableSplit$default.iterator();
                        while (it.hasNext()) {
                            Character chFirstOrNull = StringsKt.firstOrNull((String) it.next());
                            if (chFirstOrNull != null) {
                                arrayList.add(chFirstOrNull);
                            }
                            i13 = i13;
                        }
                        String strJoinToString$default = CollectionsKt.joinToString$default((List) arrayList, "", null, null, 0, null, null, 62, null);
                        if (StringsKt.isBlank(strJoinToString$default)) {
                            strJoinToString$default = "SA";
                        }
                        TextKt.m3072TextNvy7gAk(strJoinToString$default, null, Color.INSTANCE.m5134getWhite0d7_KjU(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getHeadlineMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), composer, 384, 0, 131066);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        composer.endReplaceGroup();
                    } else {
                        composer.startReplaceGroup(1738178421);
                        ComposerKt.sourceInformation(composer, "743@34414L364");
                        SingletonAsyncImageKt.m8524AsyncImagegl8XCv8($teacherProfile.getDpUrl(), "Teacher DP", ClipKt.clip(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(72)), RoundedCornerShapeKt.getCircleShape()), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, false, null, composer, 1572912, 0, 4024);
                        composer = composer;
                        composer.endReplaceGroup();
                    }
                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), composer, 6);
                    Composer composer2 = composer;
                    ComposerKt.sourceInformationMarkerStart(composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier2 = Modifier.INSTANCE;
                    Composer composer3 = composer;
                    MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap5 = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer2, modifier2);
                    Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                    int i14 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        function3 = constructor5;
                        composer2.createNode(function3);
                    } else {
                        function3 = constructor5;
                        composer2.useNode();
                    }
                    Composer composerM4367constructorimpl5 = Updater.m4367constructorimpl(composer2);
                    Updater.m4375setimpl(composerM4367constructorimpl5, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                    int i15 = (i14 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                    int i16 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer2, 516608982, "C777@36040L943:MainActivity.kt#t7vdsr");
                    Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart(composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier3 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap6 = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composer2, modifier3);
                    Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                    int i17 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        function4 = constructor6;
                        composer2.createNode(function4);
                    } else {
                        function4 = constructor6;
                        composer2.useNode();
                    }
                    Composer composerM4367constructorimpl6 = Updater.m4367constructorimpl(composer2);
                    Updater.m4375setimpl(composerM4367constructorimpl6, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl6, Integer.valueOf(iHashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                    int i18 = (i17 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                    int i19 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer2, 492037589, "C780@36242L10,781@36354L11,778@36122L292,783@36443L39,787@36716L11,790@36907L11,784@36511L446:MainActivity.kt#t7vdsr");
                    TextKt.m3072TextNvy7gAk($teacherProfile.getName(), null, MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getOnPrimaryFixed(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getTitleLarge(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), composer2, 0, 0, 131066);
                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer2, 6);
                    IconKt.m2520Iconww6aTOc(CheckCircleKt.getCheckCircle(Icons.INSTANCE.getDefault()), "Verified Faculty", MotionUtilsKt.m8745pulseGlowsW7UJKQ(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(20)), (62 & 1) != 0 ? ColorKt.Color(4279673674L) : MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getPrimary(), (62 & 2) != 0 ? 0.35f : 0.0f, (62 & 4) != 0 ? 1.0f : 0.0f, (62 & 8) != 0 ? 0.88f : 0.0f, (62 & 16) != 0 ? 1.14f : 0.0f, (62 & 32) != 0 ? 900 : 0), MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getPrimary(), composer2, 48, 0);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    if (StringsKt.isBlank($teacherProfile.getTitle())) {
                        composer2.startReplaceGroup(480737083);
                    } else {
                        composer2.startReplaceGroup(517500231);
                        ComposerKt.sourceInformation(composer2, "797@37199L10,798@37313L11,795@37078L284");
                        TextKt.m3072TextNvy7gAk($teacherProfile.getTitle(), null, MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getBodyMedium(), 0L, 0L, FontWeight.INSTANCE.getMedium(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), composer2, 0, 0, 131066);
                    }
                    composer2.endReplaceGroup();
                    if (StringsKt.isBlank($teacherProfile.getCredentials())) {
                        composer2.startReplaceGroup(480737083);
                    } else {
                        composer2.startReplaceGroup(517907881);
                        ComposerKt.sourceInformation(composer2, "805@37616L10,806@37692L11,803@37489L282");
                        String credentials = $teacherProfile.getCredentials();
                        TextStyle bodySmall = MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getBodySmall();
                        long jM2223getOnPrimaryContainer0d7_KjU = MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getOnPrimaryFixed();
                        TextKt.m3072TextNvy7gAk(credentials, null, Color.m5095copywmQWz5c(jM2223getOnPrimaryContainer0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM2223getOnPrimaryContainer0d7_KjU) : 0.8f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM2223getOnPrimaryContainer0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM2223getOnPrimaryContainer0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM2223getOnPrimaryContainer0d7_KjU) : 0.0f), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, bodySmall, composer2, 0, 0, 131066);
                    }
                    composer2.endReplaceGroup();
                    if ($teacherProfile.getRating() > 0.0d || $teacherProfile.getStudentsGuided() > 0) {
                        composer2.startReplaceGroup(518389714);
                        ComposerKt.sourceInformation(composer2, "811@37923L1937");
                        Alignment.Vertical centerVertically4 = Alignment.INSTANCE.getCenterVertically();
                        Modifier modifierM823paddingqDBjuR0$default = PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7905constructorimpl(4), 0.0f, 0.0f, 13, null);
                        ComposerKt.sourceInformationMarkerStart(composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically4, composer2, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                        CompositionLocalMap currentCompositionLocalMap7 = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composer2, modifierM823paddingqDBjuR0$default);
                        Function0<ComposeUiNode> constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                        int i20 = ((((390 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            function5 = constructor7;
                            composer2.createNode(function5);
                        } else {
                            function5 = constructor7;
                            composer2.useNode();
                        }
                        Composer composerM4367constructorimpl7 = Updater.m4367constructorimpl(composer2);
                        Updater.m4375setimpl(composerM4367constructorimpl7, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl(composerM4367constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4371initimpl(composerM4367constructorimpl7, Integer.valueOf(iHashCode7), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4373reconcileimpl(composerM4367constructorimpl7, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4375setimpl(composerM4367constructorimpl7, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                        int i21 = (i20 >> 6) & 14;
                        Composer composer4 = composer2;
                        ComposerKt.sourceInformationMarkerStart(composer4, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                        int i22 = ((390 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer4, 238785903, "C:MainActivity.kt#t7vdsr");
                        if ($teacherProfile.getRating() > 0.0d) {
                            composer4.startReplaceGroup(238790180);
                            ComposerKt.sourceInformation(composer4, "816@38215L326,824@38723L10,825@38843L11,822@38578L333");
                            IconKt.m2520Iconww6aTOc(StarKt.getStar(Icons.INSTANCE.getDefault()), "Rating", SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), ColorKt.Color(4294948864L), composer4, 3504, 0);
                            composer4 = composer4;
                            TextKt.m3072TextNvy7gAk(" " + $teacherProfile.getRating() + " ", null, MaterialTheme.INSTANCE.getColorScheme(composer4, MaterialTheme.$stable).getOnPrimaryFixed(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer4, MaterialTheme.$stable).getLabelLarge(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), composer4, 0, 0, 131066);
                        } else {
                            composer4.startReplaceGroup(200893796);
                        }
                        composer4.endReplaceGroup();
                        if ($teacherProfile.getStudentsGuided() > 0) {
                            composer4.startReplaceGroup(239624855);
                            ComposerKt.sourceInformation(composer4, "831@39175L10,832@39259L11,829@39055L291,837@39625L10,838@39709L11,834@39383L413");
                            TextStyle bodySmall2 = MaterialTheme.INSTANCE.getTypography(composer4, MaterialTheme.$stable).getBodySmall();
                            long jM2223getOnPrimaryContainer0d7_KjU2 = MaterialTheme.INSTANCE.getColorScheme(composer4, MaterialTheme.$stable).getOnPrimaryFixed();
                            TextKt.m3072TextNvy7gAk("(", null, Color.m5095copywmQWz5c(jM2223getOnPrimaryContainer0d7_KjU2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM2223getOnPrimaryContainer0d7_KjU2) : 0.7f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM2223getOnPrimaryContainer0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM2223getOnPrimaryContainer0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM2223getOnPrimaryContainer0d7_KjU2) : 0.0f), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, bodySmall2, composer4, 6, 0, 131066);
                            int studentsGuided = $teacherProfile.getStudentsGuided();
                            TextStyle bodySmall3 = MaterialTheme.INSTANCE.getTypography(composer4, MaterialTheme.$stable).getBodySmall();
                            long jM2223getOnPrimaryContainer0d7_KjU3 = MaterialTheme.INSTANCE.getColorScheme(composer4, MaterialTheme.$stable).getOnPrimaryFixed();
                            MotionUtilsKt.m8736AnimatedNumberCounterV9fs2A(studentsGuided, null, null, "+ Students Guided)", bodySmall3, Color.m5095copywmQWz5c(jM2223getOnPrimaryContainer0d7_KjU3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM2223getOnPrimaryContainer0d7_KjU3) : 0.7f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM2223getOnPrimaryContainer0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM2223getOnPrimaryContainer0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM2223getOnPrimaryContainer0d7_KjU3) : 0.0f), composer4, 3072, 6);
                        } else {
                            composer4.startReplaceGroup(200893796);
                        }
                        composer4.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                    } else {
                        composer2.startReplaceGroup(480737083);
                    }
                    composer2.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                }
                if ($teacherProfile.getSpecialties().isEmpty()) {
                    $composer.startReplaceGroup(-588370375);
                } else {
                    $composer.startReplaceGroup(-548675464);
                    ComposerKt.sourceInformation($composer, "848@40017L41,853@40299L167,849@40075L391");
                    SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), $composer, 6);
                    FlowLayoutKt.FlowRow(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(8)), Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(8)), null, 0, 0, ComposableLambdaKt.rememberComposableLambda(851675968, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda138
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return MainActivityKt.TeacherProfileCard$lambda$149$lambda$148$lambda$146($teacherProfile, (FlowRowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer, 54), $composer, 1573302, 56);
                }
                $composer.endReplaceGroup();
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), $composer, 6);
                ButtonKt.Button($onInquireClick, MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null), false, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12)), null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1300021945, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda139
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return MainActivityKt.TeacherProfileCard$lambda$149$lambda$148$lambda$147($teacherProfile, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer, 54), $composer, C.ENCODING_PCM_32BIT, LogSeverity.ERROR_VALUE);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit TeacherProfileCard$lambda$149$lambda$148$lambda$146(TeacherProfile $teacherProfile, FlowRowScope FlowRow, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(FlowRow, "$this$FlowRow");
            ComposerKt.sourceInformation($composer, "C*855@40403L23:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(851675968, $changed, -1, "com.example.sushikhasacademyapp.TeacherProfileCard.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:854)");
                }
                Iterator it = CollectionsKt.take($teacherProfile.getSpecialties(), 3).iterator();
                while (it.hasNext()) {
                    ExpertiseTag((String) it.next(), $composer, 0);
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit TeacherProfileCard$lambda$149$lambda$148$lambda$147(TeacherProfile $teacherProfile, RowScope Button, Composer $composer, int $changed) {
            String str;
            Intrinsics.checkNotNullParameter(Button, "$this$Button");
            ComposerKt.sourceInformation($composer, "C869@40789L86,870@40892L39,871@40948L122:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1300021945, $changed, -1, "com.example.sushikhasacademyapp.TeacherProfileCard.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:869)");
                }
                IconKt.m2520Iconww6aTOc(SchoolKt.getSchool(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(18)), 0L, $composer, 432, 8);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
                if (StringsKt.isBlank($teacherProfile.getName())) {
                    str = "Book Free Demo Class";
                } else {
                    str = "Book Free Demo Class with " + $teacherProfile.getName();
                }
                TextKt.m3072TextNvy7gAk(str, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        public static final void ExpertiseTag(final String text, Composer $composer, final int $changed) {
            Intrinsics.checkNotNullParameter(text, "text");
            Composer $composer2 = $composer.startRestartGroup(-436760947);
            ComposerKt.sourceInformation($composer2, "C(ExpertiseTag)N(text)880@41192L11,882@41305L11,884@41394L286,879@41153L527:MainActivity.kt#t7vdsr");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer2.changed(text) ? 4 : 2;
            }
            if ($composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-436760947, $dirty, -1, "com.example.sushikhasacademyapp.ExpertiseTag (MainActivity.kt:878)");
                }
                long jM2247getSurface0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getSurface();
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(8));
                float fM7905constructorimpl = Dp.m7905constructorimpl(1);
                long jM2236getOutline0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOutline();
                SurfaceKt.m2929SurfaceT9BRK9s(MotionUtilsKt.bounceClick$default(Modifier.INSTANCE, 0.0f, null, 3, null), roundedCornerShapeM1127RoundedCornerShape0680j_4, jM2247getSurface0d7_KjU, 0L, 0.0f, 0.0f, BorderStrokeKt.m291BorderStrokecXLIe8U(fM7905constructorimpl, Color.m5095copywmQWz5c(jM2236getOutline0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM2236getOutline0d7_KjU) : 0.3f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM2236getOutline0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM2236getOutline0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM2236getOutline0d7_KjU) : 0.0f)), ComposableLambdaKt.rememberComposableLambda(2055085352, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda148
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.ExpertiseTag$lambda$151(text, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer2, 54), $composer2, 12582912, 56);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda149
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.ExpertiseTag$lambda$152(text, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        static final Unit ExpertiseTag$lambda$151(String $text, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C888@41547L10,889@41643L11,885@41404L270:MainActivity.kt#t7vdsr");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2055085352, $changed, -1, "com.example.sushikhasacademyapp.ExpertiseTag.<anonymous> (MainActivity.kt:885)");
                }
                TextKt.m3072TextNvy7gAk($text, PaddingKt.m820paddingVpY3zN4(Modifier.INSTANCE, Dp.m7905constructorimpl(10), Dp.m7905constructorimpl(6)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), 0L, 0L, FontWeight.INSTANCE.getSemiBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 48, 0, 131064);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        public static final void WordOfTheDayCard(final TextToSpeechHelper ttsHelper, final Function0<Unit> onOpenDictionaryBrowser, Composer $composer, final int $changed) {
            Intrinsics.checkNotNullParameter(onOpenDictionaryBrowser, "onOpenDictionaryBrowser");
            Composer $composer2 = $composer.startRestartGroup(941160781);
            ComposerKt.sourceInformation($composer2, "C(WordOfTheDayCard)N(ttsHelper,onOpenDictionaryBrowser)900@41867L88,904@42045L34,905@42105L24,916@42517L68,920@42635L6039,907@42135L6539:MainActivity.kt#t7vdsr");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= ($changed & 8) == 0 ? $composer2.changed(ttsHelper) : $composer2.changedInstance(ttsHelper) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer2.changedInstance(onOpenDictionaryBrowser) ? 32 : 16;
            }
            if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(941160781, $dirty, -1, "com.example.sushikhasacademyapp.WordOfTheDayCard (MainActivity.kt:898)");
                }
                final List<ProfessionalWord> words = DictionaryData.INSTANCE.getWords();
                ComposerKt.sourceInformationMarkerStart($composer2, -1782582971, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = $composer2.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object objMutableIntStateOf = SnapshotIntStateKt.mutableIntStateOf(Random.INSTANCE.nextInt(words.size()));
                    $composer2.updateRememberedValue(objMutableIntStateOf);
                    objRememberedValue = objMutableIntStateOf;
                }
                final MutableIntState currentWordIndex$delegate = (MutableIntState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final ProfessionalWord currentWord = words.get(WordOfTheDayCard$lambda$154(currentWordIndex$delegate) % words.size());
                ComposerKt.sourceInformationMarkerStart($composer2, -1782577329, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue2 = $composer2.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer2.updateRememberedValue(objMutableStateOf$default);
                    objRememberedValue2 = objMutableStateOf$default;
                }
                final MutableState isSpeaking$delegate = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart($composer2, 683736516, "CC(remember):Effects.kt#9igjgp");
                Object objRememberedValue3 = $composer2.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    Object objCreateCompositionCoroutineScope = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2);
                    $composer2.updateRememberedValue(objCreateCompositionCoroutineScope);
                    objRememberedValue3 = objCreateCompositionCoroutineScope;
                }
                final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierM8742animatedGradientBorderrAjV9yQ$default = MotionUtilsKt.m8742animatedGradientBorderrAjV9yQ$default(PaddingKt.m821paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(16), 0.0f, 2, null), Dp.m7905constructorimpl((float) 1.5d), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16)), CollectionsKt.listOf((Object[]) new Color[]{Color.m5087boximpl(ColorKt.Color(4278355143L)), Color.m5087boximpl(ColorKt.Color(4281908728L)), Color.m5087boximpl(ColorKt.Color(4286680312L)), Color.m5087boximpl(ColorKt.Color(4278355143L))}), 0, 8, null);
                CardColors cardColorsM2144cardColorsro_MJ88 = CardDefaults.INSTANCE.m2144cardColorsro_MJ88(ColorKt.Color(4293982719L), 0L, 0L, 0L, $composer2, (CardDefaults.$stable << 12) | 6, 14);
                $composer2 = $composer2;
                CardKt.Card(modifierM8742animatedGradientBorderrAjV9yQ$default, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16)), cardColorsM2144cardColorsro_MJ88, null, null, ComposableLambdaKt.rememberComposableLambda(-724350145, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda145
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return MainActivityKt.WordOfTheDayCard$lambda$177(currentWord, ttsHelper, coroutineScope, isSpeaking$delegate, onOpenDictionaryBrowser, words, currentWordIndex$delegate, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer2, 54), $composer2, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 24);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda146
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.WordOfTheDayCard$lambda$178(ttsHelper, onOpenDictionaryBrowser, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        private static final int WordOfTheDayCard$lambda$154(MutableIntState $currentWordIndex$delegate) {
            return $currentWordIndex$delegate.getIntValue();
        }

        private static final boolean WordOfTheDayCard$lambda$157(MutableState<Boolean> mutableState) {
            return mutableState.getValue().booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void WordOfTheDayCard$lambda$158(MutableState<Boolean> mutableState, boolean z) {
            mutableState.setValue(Boolean.valueOf(z));
        }

        /* JADX WARN: Code duplicated, block: B:76:0x097a  */
        /* JADX WARN: Code duplicated, block: B:79:0x0985  */
        /* JADX WARN: Code duplicated, block: B:83:0x0a46  */
        static final Unit WordOfTheDayCard$lambda$177(final ProfessionalWord $currentWord, final TextToSpeechHelper $ttsHelper, final CoroutineScope $coroutineScope, final MutableState $isSpeaking$delegate, Function0 $onOpenDictionaryBrowser, final List $wordsList, final MutableIntState $currentWordIndex$delegate, ColumnScope Card, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Function0<ComposeUiNode> function2;
            Function0<ComposeUiNode> function3;
            Function0<ComposeUiNode> function4;
            Function0<ComposeUiNode> function5;
            Composer composer;
            Composer composer2;
            boolean zChangedInstance;
            Object objRememberedValue;
            Composer composer3;
            Intrinsics.checkNotNullParameter(Card, "$this$Card");
            ComposerKt.sourceInformation($composer, "C921@42645L6023:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-724350145, $changed, -1, "com.example.sushikhasacademyapp.WordOfTheDayCard.<anonymous> (MainActivity.kt:921)");
                }
                Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16));
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((6 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i3 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1855045462, "C922@42702L3376,999@46092L40,1003@46239L91,1001@46146L907,1024@47067L41,1026@47122L1536:MainActivity.kt#t7vdsr");
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((390 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor2;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i6 = ((390 >> 6) & 112) | 6;
                RowScope rowScope = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 611360448, "C926@42854L452,939@43324L40,943@43483L163,941@43382L1267,968@44667L1397:MainActivity.kt#t7vdsr");
                Modifier modifierM264backgroundbw27NRU = BackgroundKt.m264backgroundbw27NRU(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(44)), ColorKt.Color(4278355143L), RoundedCornerShapeKt.getCircleShape());
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierM264backgroundbw27NRU);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor3;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i9 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1899992049, "C932@43109L179:MainActivity.kt#t7vdsr");
                IconKt.m2520Iconww6aTOc(BookKt.getBook(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, Color.INSTANCE.m5134getWhite0d7_KjU(), $composer, 3120, 4);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), $composer, 6);
                Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer, -1781376932, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda65
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return MainActivityKt.WordOfTheDayCard$lambda$177$lambda$176$lambda$167$lambda$163$lambda$162((AnimatedContentTransitionScope) obj2);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue2 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                AnimatedContentKt.AnimatedContent($currentWord, modifierWeight$default, (Function1) objRememberedValue2, null, "WordTransition", null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1745189059$app(), $composer, 1597824, 40);
                Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i10 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function3 = constructor4;
                    $composer.createNode(function3);
                } else {
                    function3 = constructor4;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl4 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i11 = (i10 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i12 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1195880019, "C969@44741L208,975@44994L7,977@45068L552,976@45022L1024:MainActivity.kt#t7vdsr");
                MotionUtilsKt.m8738AudioWaveVisualizeru4SGt0E(WordOfTheDayCard$lambda$157($isSpeaking$delegate), PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, Dp.m7905constructorimpl(4), 0.0f, 11, null), ColorKt.Color(4278355143L), 0, 0.0f, $composer, 432, 24);
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd($composer);
                final Context context = (Context) objConsume;
                ComposerKt.sourceInformationMarkerStart($composer, 931264261, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChanged = $composer.changed($currentWord) | $composer.changedInstance($ttsHelper) | $composer.changedInstance(context) | $composer.changedInstance($coroutineScope);
                Object objRememberedValue3 = $composer.rememberedValue();
                if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda66
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.WordOfTheDayCard$lambda$177$lambda$176$lambda$167$lambda$166$lambda$165$lambda$164($currentWord, $ttsHelper, context, $coroutineScope, $isSpeaking$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue3 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                IconButtonKt.IconButton((Function0) objRememberedValue3, MotionUtilsKt.bounceClick$default(Modifier.INSTANCE, 0.0f, null, 3, null), false, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$2005326491$app(), $composer, 1572864, 60);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
                ComposerKt.sourceInformationMarkerStart($composer, 1325740464, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue4 = $composer.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    Object obj3 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda67
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return MainActivityKt.WordOfTheDayCard$lambda$177$lambda$176$lambda$169$lambda$168((AnimatedContentTransitionScope) obj4);
                        }
                    };
                    $composer.updateRememberedValue(obj3);
                    objRememberedValue4 = obj3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                AnimatedContentKt.AnimatedContent($currentWord, null, (Function1) objRememberedValue4, null, "MeaningTransition", null, ComposableSingletons$MainActivityKt.INSTANCE.m8675getLambda$1164486689$app(), $composer, 1597824, 42);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), $composer, 6);
                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(spaceBetween, centerVertically3, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap5 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default2);
                Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                int i13 = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function4 = constructor5;
                    $composer.createNode(function4);
                } else {
                    function4 = constructor5;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl5 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl5, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                int i14 = (i13 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                int i15 = ((438 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 820039003, "C1031@47340L859,1051@48217L427:MainActivity.kt#t7vdsr");
                Arrangement.Horizontal horizontalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(4));
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier2 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(horizontalM689spacedBy0680j_4, Alignment.INSTANCE.getTop(), $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap6 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier($composer, modifier2);
                Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                int i16 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function5 = constructor6;
                    $composer.createNode(function5);
                } else {
                    function5 = constructor6;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl6 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl6, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl6, Integer.valueOf(iHashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                int i17 = (i16 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                int i18 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -718336396, "C1033@47464L145,1032@47418L388,1042@47874L114,1041@47828L353:MainActivity.kt#t7vdsr");
                ComposerKt.sourceInformationMarkerStart($composer, 946660037, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance2 = $composer.changedInstance($wordsList);
                Object objRememberedValue5 = $composer.rememberedValue();
                if (!zChangedInstance2) {
                    composer = $composer;
                    if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ButtonKt.TextButton((Function0) objRememberedValue5, null, false, null, null, null, null, PaddingKt.m813PaddingValuesYgX7TsA(Dp.m7905constructorimpl(8), Dp.m7905constructorimpl(2)), null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1139491633$app(), composer, 817889280, 382);
                    composer2 = composer;
                    ComposerKt.sourceInformationMarkerStart(composer2, 946673126, "CC(remember):MainActivity.kt#9igjgp");
                    zChangedInstance = composer2.changedInstance($wordsList);
                    objRememberedValue = composer2.rememberedValue();
                    if (!zChangedInstance) {
                        composer3 = composer2;
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, PaddingKt.m813PaddingValuesYgX7TsA(Dp.m7905constructorimpl(8), Dp.m7905constructorimpl(2)), null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1622931944$app(), composer3, 817889280, 382);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ButtonKt.OutlinedButton($onOpenDictionaryBrowser, null, false, null, null, null, null, PaddingKt.m813PaddingValuesYgX7TsA(Dp.m7905constructorimpl(10), Dp.m7905constructorimpl(2)), null, ComposableSingletons$MainActivityKt.INSTANCE.m8686getLambda$1861318462$app(), $composer, 817889280, 382);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        composer3 = composer2;
                    }
                    objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda69
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.WordOfTheDayCard$lambda$177$lambda$176$lambda$175$lambda$174$lambda$173$lambda$172($wordsList, $currentWordIndex$delegate);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, PaddingKt.m813PaddingValuesYgX7TsA(Dp.m7905constructorimpl(8), Dp.m7905constructorimpl(2)), null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1622931944$app(), composer3, 817889280, 382);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ButtonKt.OutlinedButton($onOpenDictionaryBrowser, null, false, null, null, null, null, PaddingKt.m813PaddingValuesYgX7TsA(Dp.m7905constructorimpl(10), Dp.m7905constructorimpl(2)), null, ComposableSingletons$MainActivityKt.INSTANCE.m8686getLambda$1861318462$app(), $composer, 817889280, 382);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composer = $composer;
                }
                objRememberedValue5 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda68
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivityKt.WordOfTheDayCard$lambda$177$lambda$176$lambda$175$lambda$174$lambda$171$lambda$170($wordsList, $currentWordIndex$delegate);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue5);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ButtonKt.TextButton((Function0) objRememberedValue5, null, false, null, null, null, null, PaddingKt.m813PaddingValuesYgX7TsA(Dp.m7905constructorimpl(8), Dp.m7905constructorimpl(2)), null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1139491633$app(), composer, 817889280, 382);
                composer2 = composer;
                ComposerKt.sourceInformationMarkerStart(composer2, 946673126, "CC(remember):MainActivity.kt#9igjgp");
                zChangedInstance = composer2.changedInstance($wordsList);
                objRememberedValue = composer2.rememberedValue();
                if (!zChangedInstance) {
                    composer3 = composer2;
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, PaddingKt.m813PaddingValuesYgX7TsA(Dp.m7905constructorimpl(8), Dp.m7905constructorimpl(2)), null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1622931944$app(), composer3, 817889280, 382);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ButtonKt.OutlinedButton($onOpenDictionaryBrowser, null, false, null, null, null, null, PaddingKt.m813PaddingValuesYgX7TsA(Dp.m7905constructorimpl(10), Dp.m7905constructorimpl(2)), null, ComposableSingletons$MainActivityKt.INSTANCE.m8686getLambda$1861318462$app(), $composer, 817889280, 382);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composer3 = composer2;
                }
                objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda69
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivityKt.WordOfTheDayCard$lambda$177$lambda$176$lambda$175$lambda$174$lambda$173$lambda$172($wordsList, $currentWordIndex$delegate);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, PaddingKt.m813PaddingValuesYgX7TsA(Dp.m7905constructorimpl(8), Dp.m7905constructorimpl(2)), null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1622931944$app(), composer3, 817889280, 382);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.OutlinedButton($onOpenDictionaryBrowser, null, false, null, null, null, null, PaddingKt.m813PaddingValuesYgX7TsA(Dp.m7905constructorimpl(10), Dp.m7905constructorimpl(2)), null, ComposableSingletons$MainActivityKt.INSTANCE.m8686getLambda$1861318462$app(), $composer, 817889280, 382);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final ContentTransform WordOfTheDayCard$lambda$177$lambda$176$lambda$167$lambda$163$lambda$162(AnimatedContentTransitionScope AnimatedContent) {
            Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
            return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(250, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideInHorizontally$default(null, new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda74
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(MainActivityKt.WordOfTheDayCard$lambda$177$lambda$176$lambda$167$lambda$163$lambda$162$lambda$160(((Integer) obj).intValue()));
                }
            }, 1, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(RotationOptions.ROTATE_180, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideOutHorizontally$default(null, new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda75
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(MainActivityKt.WordOfTheDayCard$lambda$177$lambda$176$lambda$167$lambda$163$lambda$162$lambda$161(((Integer) obj).intValue()));
                }
            }, 1, null)));
        }

        static final int WordOfTheDayCard$lambda$177$lambda$176$lambda$167$lambda$163$lambda$162$lambda$160(int it) {
            return 40;
        }

        static final int WordOfTheDayCard$lambda$177$lambda$176$lambda$167$lambda$163$lambda$162$lambda$161(int it) {
            return -40;
        }

        static final Unit WordOfTheDayCard$lambda$177$lambda$176$lambda$167$lambda$166$lambda$165$lambda$164(ProfessionalWord $currentWord, TextToSpeechHelper $ttsHelper, Context $ctx, CoroutineScope $coroutineScope, MutableState $isSpeaking$delegate) {
            String speechText = "Word: " + $currentWord.getWord() + ". Meaning: " + $currentWord.getMeaning() + ". Example: " + $currentWord.getExample();
            WordOfTheDayCard$lambda$158($isSpeaking$delegate, true);
            if ($ttsHelper != null) {
                $ttsHelper.speak(speechText);
            }
            Toast.makeText($ctx, "Speaking Word of the Day...", 0).show();
            BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new MainActivityKt$WordOfTheDayCard$1$1$1$3$1$1$1($isSpeaking$delegate, null), 3, null);
            return Unit.INSTANCE;
        }

        static final ContentTransform WordOfTheDayCard$lambda$177$lambda$176$lambda$169$lambda$168(AnimatedContentTransitionScope AnimatedContent) {
            Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
            return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(250, 0, null, 6, null), 0.0f, 2, null), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(RotationOptions.ROTATE_180, 0, null, 6, null), 0.0f, 2, null));
        }

        static final Unit WordOfTheDayCard$lambda$177$lambda$176$lambda$175$lambda$174$lambda$171$lambda$170(List $wordsList, MutableIntState $currentWordIndex$delegate) {
            $currentWordIndex$delegate.setIntValue((WordOfTheDayCard$lambda$154($currentWordIndex$delegate) > 0 ? WordOfTheDayCard$lambda$154($currentWordIndex$delegate) : $wordsList.size()) - 1);
            return Unit.INSTANCE;
        }

        static final Unit WordOfTheDayCard$lambda$177$lambda$176$lambda$175$lambda$174$lambda$173$lambda$172(List $wordsList, MutableIntState $currentWordIndex$delegate) {
            $currentWordIndex$delegate.setIntValue((WordOfTheDayCard$lambda$154($currentWordIndex$delegate) + 1) % $wordsList.size());
            return Unit.INSTANCE;
        }

        public static final void DictionaryBrowserDialog(final TextToSpeechHelper ttsHelper, final Function0<Unit> onDismiss, Composer $composer, final int $changed) {
            Composer $composer2;
            int $dirty;
            Object obj;
            Intrinsics.checkNotNullParameter(onDismiss, "onDismiss");
            Composer $composer3 = $composer.startRestartGroup(-375847925);
            ComposerKt.sourceInformation($composer3, "C(DictionaryBrowserDialog)N(ttsHelper,onDismiss)1069@48808L31,1071@48904L222,1076@49155L23,1148@53069L97,1086@49601L3442,1078@49184L3988:MainActivity.kt#t7vdsr");
            int $dirty2 = $changed;
            if (($changed & 6) == 0) {
                $dirty2 |= ($changed & 8) == 0 ? $composer3.changed(ttsHelper) : $composer3.changedInstance(ttsHelper) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty2 |= $composer3.changedInstance(onDismiss) ? 32 : 16;
            }
            if (!$composer3.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-375847925, $dirty2, -1, "com.example.sushikhasacademyapp.DictionaryBrowserDialog (MainActivity.kt:1068)");
                }
                ComposerKt.sourceInformationMarkerStart($composer3, -412598870, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = $composer3.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default);
                    objRememberedValue = objMutableStateOf$default;
                }
                final MutableState searchQuery$delegate = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Object words = DictionaryData.INSTANCE.getWords();
                String strDictionaryBrowserDialog$lambda$180 = DictionaryBrowserDialog$lambda$180(searchQuery$delegate);
                ComposerKt.sourceInformationMarkerStart($composer3, -412595607, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChanged = $composer3.changed(strDictionaryBrowserDialog$lambda$180);
                Object objRememberedValue2 = $composer3.rememberedValue();
                if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    if (StringsKt.isBlank(DictionaryBrowserDialog$lambda$180(searchQuery$delegate))) {
                        $dirty = $dirty2;
                        obj = words;
                    } else {
                        Collection arrayList = new ArrayList();
                        for (Object obj2 : (Iterable) words) {
                            ProfessionalWord professionalWord = (ProfessionalWord) obj2;
                            int $dirty3 = $dirty2;
                            boolean z = zChanged;
                            if (StringsKt.contains((CharSequence) professionalWord.getWord(), (CharSequence) DictionaryBrowserDialog$lambda$180(searchQuery$delegate), true) || StringsKt.contains((CharSequence) professionalWord.getMeaning(), (CharSequence) DictionaryBrowserDialog$lambda$180(searchQuery$delegate), true)) {
                                arrayList.add(obj2);
                            }
                            zChanged = z;
                            $dirty2 = $dirty3;
                        }
                        $dirty = $dirty2;
                        obj = (List) arrayList;
                    }
                    $composer3.updateRememberedValue(obj);
                    objRememberedValue2 = obj;
                } else {
                    $dirty = $dirty2;
                }
                final List filteredWords = (List) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final LazyListState dictLazyListState = LazyListStateKt.rememberLazyListState(0, 0, $composer3, 0, 3);
                $composer2 = $composer3;
                AndroidAlertDialog_androidKt.m2066AlertDialogOix01E0(onDismiss, ComposableLambdaKt.rememberComposableLambda(1462430291, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda87
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        return MainActivityKt.DictionaryBrowserDialog$lambda$184(onDismiss, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, $composer3, 54), null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1601726039$app(), ComposableLambdaKt.rememberComposableLambda(1636549976, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda88
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        return MainActivityKt.DictionaryBrowserDialog$lambda$193(dictLazyListState, filteredWords, ttsHelper, searchQuery$delegate, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, $composer3, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, $composer2, (($dirty >> 3) & 14) | 1769520, 0, 16284);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda89
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        return MainActivityKt.DictionaryBrowserDialog$lambda$194(ttsHelper, onDismiss, $changed, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                });
            }
        }

        private static final String DictionaryBrowserDialog$lambda$180(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        static final Unit DictionaryBrowserDialog$lambda$193(final LazyListState $dictLazyListState, final List $filteredWords, final TextToSpeechHelper $ttsHelper, final MutableState $searchQuery$delegate, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            ComposerKt.sourceInformation($composer, "C1087@49615L3418:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1636549976, $changed, -1, "com.example.sushikhasacademyapp.DictionaryBrowserDialog.<anonymous> (MainActivity.kt:1087)");
                }
                Modifier modifierM851height3ABfNKs = SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(TypedValues.CycleType.TYPE_EASING));
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM851height3ABfNKs);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((6 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                int i3 = ((6 >> 6) & 112) | 6;
                BoxScope boxScope = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 697336659, "C1088@49673L3110,1140@52800L219:MainActivity.kt#t7vdsr");
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((6 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor2;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                int i6 = ((6 >> 6) & 112) | 6;
                ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 42840347, "C1091@49841L20,1089@49737L506,1100@50265L91,1101@50377L40,1107@50653L2112,1103@50439L2326:MainActivity.kt#t7vdsr");
                String strDictionaryBrowserDialog$lambda$180 = DictionaryBrowserDialog$lambda$180($searchQuery$delegate);
                Modifier modifierM823paddingqDBjuR0$default = PaddingKt.m823paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7905constructorimpl(6), 7, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1525402920, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda90
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MainActivityKt.DictionaryBrowserDialog$lambda$193$lambda$192$lambda$191$lambda$186$lambda$185($searchQuery$delegate, (String) obj);
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strDictionaryBrowserDialog$lambda$180, (Function1<? super String, Unit>) objRememberedValue, modifierM823paddingqDBjuR0$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1908615437$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1832776876$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 113246640, 12582912, 0, 8257144);
                MotionUtilsKt.m8740ScrollProgressIndicatorq9LK7_k($dictLazyListState, $filteredWords.size(), null, 0L, 0.0f, $composer, 0, 28);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(4)), $composer, 6);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(8));
                Modifier modifierWeight$default = ColumnScope.weight$default(columnScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                Arrangement.HorizontalOrVertical horizontalOrVertical = horizontalOrVerticalM689spacedBy0680j_4;
                ComposerKt.sourceInformationMarkerStart($composer, 1525430996, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance = $composer.changedInstance($filteredWords) | $composer.changed($dictLazyListState) | $composer.changedInstance($ttsHelper);
                Object objRememberedValue2 = $composer.rememberedValue();
                if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda91
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return MainActivityKt.DictionaryBrowserDialog$lambda$193$lambda$192$lambda$191$lambda$190$lambda$189($filteredWords, $dictLazyListState, $ttsHelper, (LazyListScope) obj2);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue2 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                LazyDslKt.LazyColumn(modifierWeight$default, $dictLazyListState, null, false, horizontalOrVertical, null, null, false, null, (Function1) objRememberedValue2, $composer, 24576, 492);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                MotionUtilsKt.ScrollToTopFAB($dictLazyListState, PaddingKt.m819padding3ABfNKs(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomEnd()), Dp.m7905constructorimpl(8)), 0, (CoroutineScope) null, $composer, 0, 12);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit DictionaryBrowserDialog$lambda$193$lambda$192$lambda$191$lambda$186$lambda$185(MutableState $searchQuery$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $searchQuery$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit DictionaryBrowserDialog$lambda$193$lambda$192$lambda$191$lambda$190$lambda$189(final List $filteredWords, final LazyListState $dictLazyListState, final TextToSpeechHelper $ttsHelper, LazyListScope LazyColumn) {
            Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
            LazyColumn.items($filteredWords.size(), null, new Function1<Integer, Object>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$DictionaryBrowserDialog$lambda$193$lambda$192$lambda$191$lambda$190$lambda$189$$inlined$itemsIndexed$default$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int index) {
                    $filteredWords.get(index);
                    return null;
                }
            }, ComposableLambdaKt.composableLambdaInstance(2039820996, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$DictionaryBrowserDialog$lambda$193$lambda$192$lambda$191$lambda$190$lambda$189$$inlined$itemsIndexed$default$3
                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                    invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                    Function0<ComposeUiNode> function0;
                    ComposerKt.sourceInformation($composer, "CN(it)214@10668L26:LazyDsl.kt#428nma");
                    int $dirty = $changed;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer.changed($this$items) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty |= $composer.changed(it) ? 32 : 16;
                    }
                    if ($composer.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(2039820996, $dirty, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:214)");
                        }
                        int i = ($dirty & 14) | ($dirty & 112);
                        final ProfessionalWord professionalWord = (ProfessionalWord) $filteredWords.get(it);
                        $composer.startReplaceGroup(1472011932);
                        ComposerKt.sourceInformation($composer, "CN(index,item)*1109@50752L1965:MainActivity.kt#t7vdsr");
                        Modifier modifierLazyItemScrollAnimation = MotionUtilsKt.lazyItemScrollAnimation(Modifier.INSTANCE, $dictLazyListState, it);
                        ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                        CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierLazyItemScrollAnimation);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int i2 = ((((0 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function0 = constructor;
                            $composer.createNode(function0);
                        } else {
                            function0 = constructor;
                            $composer.useNode();
                        }
                        Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                        Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        int i3 = (i2 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i4 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, 281825505, "C1112@51039L11,1112@50997L69,1113@51101L1586,1110@50861L1826:MainActivity.kt#t7vdsr");
                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        CardColors cardColorsM2144cardColorsro_MJ88 = CardDefaults.INSTANCE.m2144cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurfaceVariant(), 0L, 0L, 0L, $composer, CardDefaults.$stable << 12, 14);
                        final TextToSpeechHelper textToSpeechHelper = $ttsHelper;
                        CardKt.Card(modifierFillMaxWidth$default, null, cardColorsM2144cardColorsro_MJ88, null, null, ComposableLambdaKt.rememberComposableLambda(1977565810, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$DictionaryBrowserDialog$2$1$1$2$1$1$1$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
                                invoke(columnScope, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(ColumnScope Card, Composer $composer2, int $changed2) {
                                Function0<ComposeUiNode> function1;
                                Function0<ComposeUiNode> function2;
                                Function0<ComposeUiNode> function3;
                                Intrinsics.checkNotNullParameter(Card, "$this$Card");
                                ComposerKt.sourceInformation($composer2, "C1114@51139L1514:MainActivity.kt#t7vdsr");
                                if (!$composer2.shouldExecute(($changed2 & 17) != 16, $changed2 & 1)) {
                                    $composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1977565810, $changed2, -1, "com.example.sushikhasacademyapp.DictionaryBrowserDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:1114)");
                                }
                                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), 0.0f, 1, null);
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                final TextToSpeechHelper textToSpeechHelper2 = textToSpeechHelper;
                                final ProfessionalWord professionalWord2 = professionalWord;
                                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer2, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer2, modifierFillMaxWidth$default2);
                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                int i5 = ((((390 << 3) & 112) << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                if (!($composer2.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer2.startReusableNode();
                                if ($composer2.getInserting()) {
                                    function1 = constructor2;
                                    $composer2.createNode(function1);
                                } else {
                                    function1 = constructor2;
                                    $composer2.useNode();
                                }
                                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer2);
                                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                int i6 = (i5 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                int i7 = ((390 >> 6) & 112) | 6;
                                RowScope rowScope = RowScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart($composer2, -1627346134, "C1120@51492L742,1129@52297L115,1129@52276L339:MainActivity.kt#t7vdsr");
                                Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                CompositionLocalMap currentCompositionLocalMap3 = $composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer2, modifierWeight$default);
                                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                                int i8 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                if (!($composer2.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer2.startReusableNode();
                                if ($composer2.getInserting()) {
                                    function2 = constructor3;
                                    $composer2.createNode(function2);
                                } else {
                                    function2 = constructor3;
                                    $composer2.useNode();
                                }
                                Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl($composer2);
                                Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                                int i9 = (i8 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                int i10 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer2, -541437200, "C1121@51577L508,1126@52171L10,1126@52130L62:MainActivity.kt#t7vdsr");
                                Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                Modifier modifier = Modifier.INSTANCE;
                                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                CompositionLocalMap currentCompositionLocalMap4 = $composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer2, modifier);
                                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                                int i11 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                if (!($composer2.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer2.startReusableNode();
                                if ($composer2.getInserting()) {
                                    function3 = constructor4;
                                    $composer2.createNode(function3);
                                } else {
                                    function3 = constructor4;
                                    $composer2.useNode();
                                }
                                Composer composerM4367constructorimpl4 = Updater.m4367constructorimpl($composer2);
                                Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                                int i12 = (i11 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                int i13 = ((384 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer2, 1259769003, "C1122@51734L10,1122@51679L112,1123@51840L39,1124@51975L10,1124@52019L11,1124@51928L111:MainActivity.kt#t7vdsr");
                                TextKt.m3072TextNvy7gAk(professionalWord2.getId() + ". " + professionalWord2.getWord(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer2, MaterialTheme.$stable).getBodyMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer2, 0, 0, 131070);
                                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), $composer2, 6);
                                TextKt.m3072TextNvy7gAk(professionalWord2.getPronunciation(), null, MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer2, MaterialTheme.$stable).getBodySmall(), $composer2, 0, 0, 131066);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                $composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                TextKt.m3072TextNvy7gAk(professionalWord2.getMeaning(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer2, MaterialTheme.$stable).getBodySmall(), $composer2, 0, 0, 131070);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                $composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerStart($composer2, 1748645033, "CC(remember):MainActivity.kt#9igjgp");
                                boolean zChangedInstance = $composer2.changedInstance(textToSpeechHelper2) | $composer2.changed(professionalWord2);
                                Object objRememberedValue = $composer2.rememberedValue();
                                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    Object obj = (Function0) new Function0<Unit>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$DictionaryBrowserDialog$2$1$1$2$1$1$1$1$1$2$1
                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            TextToSpeechHelper textToSpeechHelper3 = textToSpeechHelper2;
                                            if (textToSpeechHelper3 != null) {
                                                textToSpeechHelper3.speak(professionalWord2.getWord());
                                            }
                                        }
                                    };
                                    $composer2.updateRememberedValue(obj);
                                    objRememberedValue = obj;
                                }
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1109149940$app(), $composer2, 1572864, 62);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                $composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer, 54), $composer, 196614, 26);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endReplaceGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer.skipToGroupEnd();
                }
            }));
            return Unit.INSTANCE;
        }

        static final Unit DictionaryBrowserDialog$lambda$184(Function0 $onDismiss, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C1149@53083L73:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1462430291, $changed, -1, "com.example.sushikhasacademyapp.DictionaryBrowserDialog.<anonymous> (MainActivity.kt:1149)");
                }
                ButtonKt.Button($onDismiss, null, false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$625657411$app(), $composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_POSITION_TYPE);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        public static final void CategoryFilterBar(final String selectedCategory, final Function1<? super String, Unit> onCategorySelected, Composer $composer, final int $changed) {
            Intrinsics.checkNotNullParameter(selectedCategory, "selectedCategory");
            Intrinsics.checkNotNullParameter(onCategorySelected, "onCategorySelected");
            Composer $composer2 = $composer.startRestartGroup(-710550238);
            ComposerKt.sourceInformation($composer2, "C(CategoryFilterBar)N(selectedCategory,onCategorySelected)1162@53520L2188,1159@53386L2322:MainActivity.kt#t7vdsr");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer2.changed(selectedCategory) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer2.changedInstance(onCategorySelected) ? 32 : 16;
            }
            if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-710550238, $dirty, -1, "com.example.sushikhasacademyapp.CategoryFilterBar (MainActivity.kt:1157)");
                }
                final List categories = CollectionsKt.listOf((Object[]) new String[]{"All Courses", "Spoken English", "Grammar", "Competitive Exams", "Corporate"});
                PaddingValues paddingValuesM814PaddingValuesYgX7TsA$default = PaddingKt.m814PaddingValuesYgX7TsA$default(Dp.m7905constructorimpl(16), 0.0f, 2, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart($composer2, 61034542, "CC(remember):MainActivity.kt#9igjgp");
                boolean z = (($dirty & 14) == 4) | (($dirty & 112) == 32);
                Object objRememberedValue = $composer2.rememberedValue();
                if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda40
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return MainActivityKt.CategoryFilterBar$lambda$202$lambda$201(categories, selectedCategory, onCategorySelected, (LazyListScope) obj2);
                        }
                    };
                    $composer2.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LazyDslKt.LazyRow(null, null, paddingValuesM814PaddingValuesYgX7TsA$default, false, horizontalOrVerticalM689spacedBy0680j_4, null, null, false, null, (Function1) objRememberedValue, $composer2, 24960, 491);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda41
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return MainActivityKt.CategoryFilterBar$lambda$203(selectedCategory, onCategorySelected, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }

        static final Unit CategoryFilterBar$lambda$202$lambda$201(final List $categories, final String $selectedCategory, final Function1 $onCategorySelected, LazyListScope LazyRow) {
            Intrinsics.checkNotNullParameter(LazyRow, "$this$LazyRow");
            final Function1 function1 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$CategoryFilterBar$lambda$202$lambda$201$$inlined$items$default$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    return invoke((String) p1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(String str) {
                    return null;
                }
            };
            LazyRow.items($categories.size(), null, new Function1<Integer, Object>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$CategoryFilterBar$lambda$202$lambda$201$$inlined$items$default$3
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int index) {
                    return function1.invoke($categories.get(index));
                }
            }, ComposableLambdaKt.composableLambdaInstance(802480018, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$CategoryFilterBar$lambda$202$lambda$201$$inlined$items$default$4
                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                    invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                    long surfaceVariant;
                    long onSurfaceVariant;
                    ComposerKt.sourceInformation($composer, "CN(it)178@8834L22:LazyDsl.kt#428nma");
                    int $dirty = $changed;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer.changed($this$items) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty |= $composer.changed(it) ? 32 : 16;
                    }
                    if (!$composer.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                        $composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(802480018, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:178)");
                    }
                    int i = $dirty & 14;
                    final String str = (String) $categories.get(it);
                    $composer.startReplaceGroup(144762069);
                    ComposerKt.sourceInformation($composer, "CN(category)*1165@53645L229,1170@53909L246,1175@54186L228,1186@54704L89,1182@54463L32,1190@54808L884,1181@54428L1264:MainActivity.kt#t7vdsr");
                    final boolean zAreEqual = Intrinsics.areEqual($selectedCategory, str);
                    final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(zAreEqual ? 1.05f : 1.0f, AnimationSpecKt.spring$default(0.5f, 0.0f, null, 6, null), 0.0f, "CategoryChipScale", null, $composer, 3120, 20);
                    if (zAreEqual) {
                        $composer.startReplaceGroup(2082891311);
                        ComposerKt.sourceInformation($composer, "1171@53990L11");
                        surfaceVariant = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
                    } else {
                        $composer.startReplaceGroup(2082892566);
                        ComposerKt.sourceInformation($composer, "1171@54029L11");
                        surfaceVariant = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurfaceVariant();
                    }
                    $composer.endReplaceGroup();
                    State<Color> stateM135animateColorAsStateeuL9pac = SingleValueAnimationKt.m135animateColorAsStateeuL9pac(surfaceVariant, AnimationSpecKt.tween$default(250, 0, null, 6, null), "CategoryChipBg", null, $composer, 432, 8);
                    if (zAreEqual) {
                        $composer.startReplaceGroup(2082899533);
                        $composer.endReplaceGroup();
                        onSurfaceVariant = Color.INSTANCE.m5134getWhite0d7_KjU();
                    } else {
                        $composer.startReplaceGroup(2082900728);
                        ComposerKt.sourceInformation($composer, "1176@54284L11");
                        onSurfaceVariant = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant();
                        $composer.endReplaceGroup();
                    }
                    final State<Color> stateM135animateColorAsStateeuL9pac2 = SingleValueAnimationKt.m135animateColorAsStateeuL9pac(onSurfaceVariant, AnimationSpecKt.tween$default(250, 0, null, 6, null), "CategoryChipText", null, $composer, 432, 8);
                    RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(20));
                    long jCategoryFilterBar$lambda$202$lambda$201$lambda$200$lambda$196 = MainActivityKt.CategoryFilterBar$lambda$202$lambda$201$lambda$200$lambda$196(stateM135animateColorAsStateeuL9pac);
                    float fM7905constructorimpl = zAreEqual ? Dp.m7905constructorimpl(4) : Dp.m7905constructorimpl(0);
                    Modifier.Companion companion = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, 2082913857, "CC(remember):MainActivity.kt#9igjgp");
                    boolean zChanged = $composer.changed(stateAnimateFloatAsState);
                    Object objRememberedValue = $composer.rememberedValue();
                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$CategoryFilterBar$1$1$1$1$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                invoke2(graphicsLayerScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(GraphicsLayerScope graphicsLayer) {
                                Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
                                graphicsLayer.setScaleX(MainActivityKt.CategoryFilterBar$lambda$202$lambda$201$lambda$200$lambda$195(stateAnimateFloatAsState));
                                graphicsLayer.setScaleY(MainActivityKt.CategoryFilterBar$lambda$202$lambda$201$lambda$200$lambda$195(stateAnimateFloatAsState));
                            }
                        };
                        $composer.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) objRememberedValue);
                    ComposerKt.sourceInformationMarkerStart($composer, 2082906088, "CC(remember):MainActivity.kt#9igjgp");
                    boolean zChanged2 = $composer.changed($onCategorySelected) | ((((i & 112) ^ 48) > 32 && $composer.changed(str)) || (i & 48) == 32);
                    Object objRememberedValue2 = $composer.rememberedValue();
                    if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        final Function1 function2 = $onCategorySelected;
                        Object obj = (Function0) new Function0<Unit>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$CategoryFilterBar$1$1$1$2$1
                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                function2.invoke(str);
                            }
                        };
                        $composer.updateRememberedValue(obj);
                        objRememberedValue2 = obj;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SurfaceKt.m2932Surfaceo_FOJdg((Function0) objRememberedValue2, modifierGraphicsLayer, false, roundedCornerShapeM1127RoundedCornerShape0680j_4, jCategoryFilterBar$lambda$202$lambda$201$lambda$200$lambda$196, 0L, 0.0f, fM7905constructorimpl, null, null, ComposableLambdaKt.rememberComposableLambda(-260574083, true, new Function2<Composer, Integer, Unit>() { // from class: com.example.sushikhasacademyapp.MainActivityKt$CategoryFilterBar$1$1$1$3
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer2, int $changed2) {
                            Function0<ComposeUiNode> function0;
                            ComposerKt.sourceInformation($composer2, "C1191@54826L852:MainActivity.kt#t7vdsr");
                            if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                                $composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-260574083, $changed2, -1, "com.example.sushikhasacademyapp.CategoryFilterBar.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:1191)");
                            }
                            Modifier modifierM820paddingVpY3zN4 = PaddingKt.m820paddingVpY3zN4(Modifier.INSTANCE, Dp.m7905constructorimpl(14), Dp.m7905constructorimpl(8));
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            boolean z = zAreEqual;
                            String str2 = str;
                            State<Color> state = stateM135animateColorAsStateeuL9pac2;
                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer2, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM820paddingVpY3zN4);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int i2 = ((((390 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                                function0 = constructor;
                                $composer2.createNode(function0);
                            } else {
                                function0 = constructor;
                                $composer2.useNode();
                            }
                            Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer2);
                            Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            int i3 = (i2 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            int i4 = ((390 >> 6) & 112) | 6;
                            Composer composer = $composer2;
                            ComposerKt.sourceInformationMarkerStart(composer, -703460338, "C1206@55498L10,1204@55405L255:MainActivity.kt#t7vdsr");
                            if (z) {
                                composer.startReplaceGroup(-703453519);
                                ComposerKt.sourceInformation(composer, "1196@55066L243,1202@55334L28");
                                IconKt.m2520Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), Color.INSTANCE.m5134getWhite0d7_KjU(), composer, 3504, 0);
                                composer = composer;
                                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer, 6);
                            } else {
                                composer.startReplaceGroup(-758064855);
                            }
                            composer.endReplaceGroup();
                            TextStyle labelMedium = MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getLabelMedium();
                            FontWeight.Companion companion2 = FontWeight.INSTANCE;
                            Composer composer2 = composer;
                            TextKt.m3072TextNvy7gAk(str2, null, MainActivityKt.CategoryFilterBar$lambda$202$lambda$201$lambda$200$lambda$197(state), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(labelMedium, 0L, 0L, z ? companion2.getBold() : companion2.getMedium(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), composer2, 0, 0, 131066);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer, 54), $composer, 0, 6, 868);
                    $composer.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }));
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float CategoryFilterBar$lambda$202$lambda$201$lambda$200$lambda$195(State<Float> state) {
            return ((Number) state.getValue()).floatValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long CategoryFilterBar$lambda$202$lambda$201$lambda$200$lambda$196(State<Color> state) {
            return ((Color) state.getValue()).m5107unboximpl();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long CategoryFilterBar$lambda$202$lambda$201$lambda$200$lambda$197(State<Color> state) {
            return ((Color) state.getValue()).m5107unboximpl();
        }

        public static final void SectionHeader(final String title, String subtitle, Composer $composer, final int $changed) {
            final String str;
            Composer $composer2;
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(subtitle, "subtitle");
            Composer $composer3 = $composer.startRestartGroup(-118425389);
            ComposerKt.sourceInformation($composer3, "C(SectionHeader)N(title,subtitle)1217@55781L524:MainActivity.kt#t7vdsr");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer3.changed(title) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer3.changed(subtitle) ? 32 : 16;
            }
            if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                str = subtitle;
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-118425389, $dirty, -1, "com.example.sushikhasacademyapp.SectionHeader (MainActivity.kt:1216)");
                }
                Modifier modifierM820paddingVpY3zN4 = PaddingKt.m820paddingVpY3zN4(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(16), Dp.m7905constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM820paddingVpY3zN4);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((6 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor);
                } else {
                    $composer3.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i3 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 2037206238, "C1224@55985L10,1225@56078L11,1222@55919L193,1229@56190L10,1230@56246L11,1227@56121L178:MainActivity.kt#t7vdsr");
                $composer2 = $composer3;
                TextKt.m3072TextNvy7gAk(title, null, MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnBackground(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer3, $dirty & 14, 0, 131066);
                TextStyle bodySmall = MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getBodySmall();
                long jM2219getOnBackground0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnBackground();
                str = subtitle;
                TextKt.m3072TextNvy7gAk(str, null, Color.m5095copywmQWz5c(jM2219getOnBackground0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM2219getOnBackground0d7_KjU) : 0.7f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM2219getOnBackground0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM2219getOnBackground0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM2219getOnBackground0d7_KjU) : 0.0f), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, bodySmall, $composer3, ($dirty >> 3) & 14, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda56
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.SectionHeader$lambda$205(title, str, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:53:0x0142  */
        public static final void CourseCard(final CourseInfo course, final Function0<Unit> onEnrollClick, Composer $composer, final int $changed) {
            ImageVector iconVector;
            Intrinsics.checkNotNullParameter(course, "course");
            Intrinsics.checkNotNullParameter(onEnrollClick, "onEnrollClick");
            Composer $composer2 = $composer.startRestartGroup(-1371698769);
            ComposerKt.sourceInformation($composer2, "C(CourseCard)N(course,onEnrollClick)1237@56413L39,1238@56492L25,1239@56540L34,1241@56597L194,1258@57151L85,1262@57318L56,1266@57491L11,1266@57449L62,1267@57546L38,1268@57591L4396,1254@57004L4983:MainActivity.kt#t7vdsr");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= ($changed & 8) == 0 ? $composer2.changed(course) : $composer2.changedInstance(course) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer2.changedInstance(onEnrollClick) ? 32 : 16;
            }
            int $dirty2 = $dirty;
            if ($composer2.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1371698769, $dirty2, -1, "com.example.sushikhasacademyapp.CourseCard (MainActivity.kt:1236)");
                }
                ComposerKt.sourceInformationMarkerStart($composer2, 352446230, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = $composer2.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object objMutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                    $composer2.updateRememberedValue(objMutableInteractionSource);
                    objRememberedValue = objMutableInteractionSource;
                }
                MutableInteractionSource interactionSource = (MutableInteractionSource) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                State<Boolean> stateCollectIsPressedAsState = PressInteractionKt.collectIsPressedAsState(interactionSource, $composer2, 6);
                ComposerKt.sourceInformationMarkerStart($composer2, 352450289, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue2 = $composer2.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer2.updateRememberedValue(objMutableStateOf$default);
                    objRememberedValue2 = objMutableStateOf$default;
                }
                final MutableState isExpanded$delegate = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(CourseCard$lambda$207(stateCollectIsPressedAsState) ? 0.97f : 1.0f, AnimationSpecKt.spring$default(0.5f, 0.0f, null, 6, null), 0.0f, "CourseCardScale", null, $composer2, 3120, 20);
                switch (course.getIconName()) {
                    case "Person":
                        iconVector = PersonKt.getPerson(Icons.INSTANCE.getDefault());
                        break;
                    case "School":
                        iconVector = SchoolKt.getSchool(Icons.INSTANCE.getDefault());
                        break;
                    case "Book":
                        iconVector = BookKt.getBook(Icons.INSTANCE.getDefault());
                        break;
                    default:
                        iconVector = CallKt.getCall(Icons.INSTANCE.getDefault());
                        break;
                }
                Modifier modifierM820paddingVpY3zN4 = PaddingKt.m820paddingVpY3zN4(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(16), Dp.m7905constructorimpl(6));
                ComposerKt.sourceInformationMarkerStart($composer2, 352469892, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChanged = $composer2.changed(stateAnimateFloatAsState);
                Object objRememberedValue3 = $composer2.rememberedValue();
                if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda58
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return MainActivityKt.CourseCard$lambda$213$lambda$212(stateAnimateFloatAsState, (GraphicsLayerScope) obj2);
                        }
                    };
                    $composer2.updateRememberedValue(obj);
                    objRememberedValue3 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierM820paddingVpY3zN4, (Function1) objRememberedValue3);
                ComposerKt.sourceInformationMarkerStart($composer2, 352475207, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue4 = $composer2.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda59
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.CourseCard$lambda$215$lambda$214(isExpanded$delegate);
                        }
                    };
                    $composer2.updateRememberedValue(obj2);
                    objRememberedValue4 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierM295clickableO2vRcR0 = ClickableKt.m295clickableO2vRcR0(modifierGraphicsLayer, interactionSource, null, (28 & 4) != 0, (28 & 8) != 0 ? null : null, (28 & 16) != 0 ? null : null, (Function0) objRememberedValue4);
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(18));
                final ImageVector iconVector2 = iconVector;
                CardColors cardColorsM2144cardColorsro_MJ88 = CardDefaults.INSTANCE.m2144cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getSurface(), 0L, 0L, 0L, $composer2, CardDefaults.$stable << 12, 14);
                CardElevation cardElevationM2145cardElevationaqJV_2Y = CardDefaults.INSTANCE.m2145cardElevationaqJV_2Y(Dp.m7905constructorimpl(3), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer2, (CardDefaults.$stable << 18) | 6, 62);
                $composer2 = $composer2;
                CardKt.Card(modifierM295clickableO2vRcR0, roundedCornerShapeM1127RoundedCornerShape0680j_4, cardColorsM2144cardColorsro_MJ88, cardElevationM2145cardElevationaqJV_2Y, null, ComposableLambdaKt.rememberComposableLambda(497568545, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda60
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj3, Object obj4, Object obj5) {
                        return MainActivityKt.CourseCard$lambda$224(course, iconVector2, isExpanded$delegate, onEnrollClick, (ColumnScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, $composer2, 54), $composer2, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 16);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda61
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        return MainActivityKt.CourseCard$lambda$225(course, onEnrollClick, $changed, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                });
            }
        }

        private static final boolean CourseCard$lambda$207(State<Boolean> state) {
            return ((Boolean) state.getValue()).booleanValue();
        }

        private static final boolean CourseCard$lambda$209(MutableState<Boolean> mutableState) {
            return mutableState.getValue().booleanValue();
        }

        private static final void CourseCard$lambda$210(MutableState<Boolean> mutableState, boolean z) {
            mutableState.setValue(Boolean.valueOf(z));
        }

        private static final float CourseCard$lambda$211(State<Float> state) {
            return ((Number) state.getValue()).floatValue();
        }

        static final Unit CourseCard$lambda$213$lambda$212(State $cardScale$delegate, GraphicsLayerScope graphicsLayer) {
            Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
            graphicsLayer.setScaleX(CourseCard$lambda$211($cardScale$delegate));
            graphicsLayer.setScaleY(CourseCard$lambda$211($cardScale$delegate));
            return Unit.INSTANCE;
        }

        static final Unit CourseCard$lambda$215$lambda$214(MutableState $isExpanded$delegate) {
            CourseCard$lambda$210($isExpanded$delegate, !CourseCard$lambda$209($isExpanded$delegate));
            return Unit.INSTANCE;
        }

        static final Unit CourseCard$lambda$224(final CourseInfo $course, ImageVector $iconVector, MutableState $isExpanded$delegate, Function0 $onEnrollClick, ColumnScope Card, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Function0<ComposeUiNode> function2;
            Function0<ComposeUiNode> function3;
            Function0<ComposeUiNode> function4;
            Function0<ComposeUiNode> function5;
            Function0<ComposeUiNode> function6;
            Intrinsics.checkNotNullParameter(Card, "$this$Card");
            ComposerKt.sourceInformation($composer, "C1269@57601L4380:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(497568545, $changed, -1, "com.example.sushikhasacademyapp.CourseCard.<anonymous> (MainActivity.kt:1269)");
                }
                Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(AnimationModifierKt.animateContentSize$default(Modifier.INSTANCE, AnimationSpecKt.spring$default(0.75f, 0.0f, null, 6, null), null, 2, null), Dp.m7905constructorimpl(16));
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i3 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -768110941, "C1274@57802L1899,1319@59715L41,1323@59857L10,1324@59917L11,1321@59770L304,1329@60088L41,1331@60143L1828:MainActivity.kt#t7vdsr");
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor2;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i6 = ((384 >> 6) & 112) | 6;
                RowScope rowScope = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 27490703, "C1280@58034L11,1277@57902L505,1290@58425L40,1292@58483L956,1314@59544L10,1315@59650L11,1312@59457L230:MainActivity.kt#t7vdsr");
                Modifier modifierM264backgroundbw27NRU = BackgroundKt.m264backgroundbw27NRU(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(46)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimaryContainer(), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12)));
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierM264backgroundbw27NRU);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor3;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i9 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1527215487, "C1286@58348L11,1283@58196L193:MainActivity.kt#t7vdsr");
                IconKt.m2520Iconww6aTOc($iconVector, (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), $composer, 6);
                Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i10 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function3 = constructor4;
                    $composer.createNode(function3);
                } else {
                    function3 = constructor4;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl4 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i11 = (i10 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i12 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -155499803, "C1295@58641L10,1293@58544L176,1297@58741L680:MainActivity.kt#t7vdsr");
                TextKt.m3072TextNvy7gAk($course.getTitle(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 0, 0, 131070);
                Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier2 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap5 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier($composer, modifier2);
                Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                int i13 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function4 = constructor5;
                    $composer.createNode(function4);
                } else {
                    function4 = constructor5;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl5 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl5, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                int i14 = (i13 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i15 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1771617398, "C1300@58936L10,1301@59008L11,1298@58819L234,1303@59119L10,1303@59078L62,1306@59273L10,1307@59345L11,1304@59165L234:MainActivity.kt#t7vdsr");
                TextKt.m3072TextNvy7gAk("Level: " + $course.getLevel(), null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131066);
                TextKt.m3072TextNvy7gAk(" • ", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 131070);
                TextKt.m3072TextNvy7gAk($course.getDuration(), null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                TextKt.m3072TextNvy7gAk($course.getFee(), null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getExtraBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 0, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), $composer, 6);
                TextKt.m3072TextNvy7gAk($course.getDescription(), null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m7816getEllipsisgIe3tQ8(), false, CourseCard$lambda$209($isExpanded$delegate) ? Integer.MAX_VALUE : 2, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 384, 110586);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), $composer, 6);
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(spaceBetween, centerVertically3, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap6 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
                Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                int i16 = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function5 = constructor6;
                    $composer.createNode(function5);
                } else {
                    function5 = constructor6;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl6 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl6, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl6, Integer.valueOf(iHashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                int i17 = (i16 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                int i18 = ((438 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -122539051, "C1336@60361L1221,1362@61600L357:MainActivity.kt#t7vdsr");
                Alignment.Vertical centerVertically4 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier3 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically4, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap7 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier($composer, modifier3);
                Function0<ComposeUiNode> constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                int i19 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function6 = constructor7;
                    $composer.createNode(function6);
                } else {
                    function6 = constructor7;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl7 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl7, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl7, Integer.valueOf(iHashCode7), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl7, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl7, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                int i20 = (i19 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                int i21 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -2033983772, "C1337@60435L242,1343@60698L39,1346@60861L10,1344@60758L182,1348@60961L40,1350@61077L11,1352@61188L376,1349@61022L542:MainActivity.kt#t7vdsr");
                IconKt.m2520Iconww6aTOc(StarKt.getStar(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), ColorKt.Color(4294948864L), $composer, 3504, 0);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(4)), $composer, 6);
                TextKt.m3072TextNvy7gAk(String.valueOf($course.getRating()), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 0, 0, 131070);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), $composer, 6);
                SurfaceKt.m2929SurfaceT9BRK9s(null, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(6)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSecondaryContainer(), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1583458289, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda44
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.CourseCard$lambda$224$lambda$223$lambda$222$lambda$221$lambda$220($course, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer, 54), $composer, 12582912, 121);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.Button($onEnrollClick, MotionUtilsKt.bounceClick$default(Modifier.INSTANCE, 0.0f, null, 3, null), false, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(10)), null, null, null, PaddingKt.m813PaddingValuesYgX7TsA(Dp.m7905constructorimpl(16), Dp.m7905constructorimpl(4)), null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$997702114$app(), $composer, 817889280, 372);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit CourseCard$lambda$224$lambda$223$lambda$222$lambda$221$lambda$220(CourseInfo $course, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C1356@61411L10,1357@61484L11,1353@61214L328:MainActivity.kt#t7vdsr");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1583458289, $changed, -1, "com.example.sushikhasacademyapp.CourseCard.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:1353)");
                }
                TextKt.m3072TextNvy7gAk($course.getMode(), PaddingKt.m820paddingVpY3zN4(Modifier.INSTANCE, Dp.m7905constructorimpl(8), Dp.m7905constructorimpl(2)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSecondaryContainer(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 48, 0, 131064);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        public static final void BatchCard(final BatchSchedule batch, final Function0<Unit> onSelectBatch, Composer $composer, final int $changed) {
            Composer $composer2;
            Intrinsics.checkNotNullParameter(batch, "batch");
            Intrinsics.checkNotNullParameter(onSelectBatch, "onSelectBatch");
            Composer $composer3 = $composer.startRestartGroup(-212120132);
            ComposerKt.sourceInformation($composer3, "C(BatchCard)N(batch,onSelectBatch)1377@62096L39,1378@62175L25,1380@62223L193,1389@62510L85,1395@62818L11,1395@62776L69,1396@62880L38,1397@62925L2857,1386@62422L3360:MainActivity.kt#t7vdsr");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= ($changed & 8) == 0 ? $composer3.changed(batch) : $composer3.changedInstance(batch) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer3.changedInstance(onSelectBatch) ? 32 : 16;
            }
            if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-212120132, $dirty, -1, "com.example.sushikhasacademyapp.BatchCard (MainActivity.kt:1376)");
                }
                ComposerKt.sourceInformationMarkerStart($composer3, -1983980541, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = $composer3.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object objMutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(objMutableInteractionSource);
                    objRememberedValue = objMutableInteractionSource;
                }
                MutableInteractionSource interactionSource = (MutableInteractionSource) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(BatchCard$lambda$227(PressInteractionKt.collectIsPressedAsState(interactionSource, $composer3, 6)) ? 0.95f : 1.0f, AnimationSpecKt.spring$default(0.5f, 0.0f, null, 6, null), 0.0f, "BatchCardScale", null, $composer3, 3120, 20);
                Modifier modifierM870width3ABfNKs = SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(260));
                ComposerKt.sourceInformationMarkerStart($composer3, -1983967247, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChanged = $composer3.changed(stateAnimateFloatAsState);
                Object objRememberedValue2 = $composer3.rememberedValue();
                if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda37
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return MainActivityKt.BatchCard$lambda$230$lambda$229(stateAnimateFloatAsState, (GraphicsLayerScope) obj2);
                        }
                    };
                    $composer3.updateRememberedValue(obj);
                    objRememberedValue2 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                CardKt.Card(ClickableKt.m295clickableO2vRcR0(GraphicsLayerModifierKt.graphicsLayer(modifierM870width3ABfNKs, (Function1) objRememberedValue2), interactionSource, null, (28 & 4) != 0, (28 & 8) != 0 ? null : null, (28 & 16) != 0 ? null : null, onSelectBatch), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16)), CardDefaults.INSTANCE.m2144cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurfaceVariant(), 0L, 0L, 0L, $composer3, CardDefaults.$stable << 12, 14), CardDefaults.INSTANCE.m2145cardElevationaqJV_2Y(Dp.m7905constructorimpl(3), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, (CardDefaults.$stable << 18) | 6, 62), null, ComposableLambdaKt.rememberComposableLambda(857438894, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda38
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        return MainActivityKt.BatchCard$lambda$236(batch, (ColumnScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, $composer3, 54), $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 16);
                $composer2 = $composer3;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda39
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return MainActivityKt.BatchCard$lambda$237(batch, onSelectBatch, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }

        private static final boolean BatchCard$lambda$227(State<Boolean> state) {
            return ((Boolean) state.getValue()).booleanValue();
        }

        private static final float BatchCard$lambda$228(State<Float> state) {
            return ((Number) state.getValue()).floatValue();
        }

        static final Unit BatchCard$lambda$230$lambda$229(State $cardScale$delegate, GraphicsLayerScope graphicsLayer) {
            Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
            graphicsLayer.setScaleX(BatchCard$lambda$228($cardScale$delegate));
            graphicsLayer.setScaleY(BatchCard$lambda$228($cardScale$delegate));
            return Unit.INSTANCE;
        }

        static final Unit BatchCard$lambda$236(final BatchSchedule $batch, ColumnScope Card, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Modifier.Companion companionM8745pulseGlowsW7UJKQ;
            Function0<ComposeUiNode> function2;
            Function0<ComposeUiNode> function3;
            Intrinsics.checkNotNullParameter(Card, "$this$Card");
            ComposerKt.sourceInformation($composer, "C1398@62935L2841:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(857438894, $changed, -1, "com.example.sushikhasacademyapp.BatchCard.<anonymous> (MainActivity.kt:1398)");
                }
                Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16));
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((6 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i3 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 638059657, "C1399@62992L1206,1424@64212L41,1428@64353L10,1426@64267L236,1433@64517L40,1435@64571L526,1449@65111L40,1451@65165L601:MainActivity.kt#t7vdsr");
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor2;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i6 = ((438 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 774217517, "C1408@63492L444,1404@63210L726,1419@64063L10,1417@63954L230:MainActivity.kt#t7vdsr");
                long jColor = ColorKt.Color($batch.isOnline() ? 4292672743L : 4294898631L);
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(6));
                if ($batch.isOnline()) {
                    companionM8745pulseGlowsW7UJKQ = MotionUtilsKt.m8745pulseGlowsW7UJKQ(Modifier.INSTANCE, (62 & 1) != 0 ? ColorKt.Color(4279673674L) : ColorKt.Color(4279673674L), (62 & 2) != 0 ? 0.35f : 0.0f, (62 & 4) != 0 ? 1.0f : 0.0f, (62 & 8) != 0 ? 0.88f : 0.0f, (62 & 16) != 0 ? 1.14f : 0.0f, (62 & 32) != 0 ? 900 : 0);
                } else {
                    companionM8745pulseGlowsW7UJKQ = Modifier.INSTANCE;
                }
                SurfaceKt.m2929SurfaceT9BRK9s(companionM8745pulseGlowsW7UJKQ, roundedCornerShapeM1127RoundedCornerShape0680j_4, jColor, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(442566499, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.BatchCard$lambda$236$lambda$235$lambda$232$lambda$231($batch, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer, 54), $composer, 12582912, MenuKt.InTransitionDuration);
                TextKt.m3072TextNvy7gAk($batch.getSeatsLeft() + " seats left!", null, ColorKt.Color(4292617766L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 384, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), $composer, 6);
                TextKt.m3072TextNvy7gAk($batch.getCourseTitle(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m7816getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleSmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 0, 24960, 110590);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), $composer, 6);
                Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor3;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                int i9 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1664841302, "C1440@64846L11,1436@64641L242,1442@64900L39,1445@65045L10,1443@64956L127:MainActivity.kt#t7vdsr");
                IconKt.m2520Iconww6aTOc(ScheduleKt.getSchedule(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 432, 0);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), $composer, 6);
                TextKt.m3072TextNvy7gAk($batch.getTiming(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131070);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(4)), $composer, 6);
                Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier2 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifier2);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i10 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function3 = constructor4;
                    $composer.createNode(function3);
                } else {
                    function3 = constructor4;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl4 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i11 = (i10 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                int i12 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -2092108706, "C1456@65436L11,1452@65235L247,1458@65499L39,1461@65642L10,1462@65706L11,1459@65555L197:MainActivity.kt#t7vdsr");
                IconKt.m2520Iconww6aTOc(InfoKt.getInfo(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), $composer, 432, 0);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), $composer, 6);
                TextKt.m3072TextNvy7gAk($batch.getDays(), null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit BatchCard$lambda$236$lambda$235$lambda$232$lambda$231(BatchSchedule $batch, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C1412@63746L10,1409@63514L404:MainActivity.kt#t7vdsr");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(442566499, $changed, -1, "com.example.sushikhasacademyapp.BatchCard.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:1409)");
                }
                TextKt.m3072TextNvy7gAk($batch.isOnline() ? "Live Online" : "Offline Classroom", PaddingKt.m820paddingVpY3zN4(Modifier.INSTANCE, Dp.m7905constructorimpl(8), Dp.m7905constructorimpl(2)), ColorKt.Color($batch.isOnline() ? 4279599165L : 4290007817L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 48, 0, 131064);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        public static final void WhyChooseUsSection(final String teacherName, Composer $composer, final int $changed) {
            Composer $composer2;
            Intrinsics.checkNotNullParameter(teacherName, "teacherName");
            Composer $composer3 = $composer.startRestartGroup(1576485646);
            ComposerKt.sourceInformation($composer3, "C(WhyChooseUsSection)N(teacherName)1475@66024L11,1475@65982L62,1477@66122L38,1478@66167L1401,1471@65848L1720:MainActivity.kt#t7vdsr");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer3.changed(teacherName) ? 4 : 2;
            }
            int $dirty2 = $dirty;
            if (!$composer3.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1576485646, $dirty2, -1, "com.example.sushikhasacademyapp.WhyChooseUsSection (MainActivity.kt:1470)");
                }
                CardKt.Card(PaddingKt.m821paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(16), 0.0f, 2, null), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16)), CardDefaults.INSTANCE.m2144cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurface(), 0L, 0L, 0L, $composer3, CardDefaults.$stable << 12, 14), CardDefaults.INSTANCE.m2145cardElevationaqJV_2Y(Dp.m7905constructorimpl(2), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, (CardDefaults.$stable << 18) | 6, 62), null, ComposableLambdaKt.rememberComposableLambda(1596228096, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda83
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return MainActivityKt.WhyChooseUsSection$lambda$239(teacherName, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer3, 54), $composer3, 196614, 16);
                $composer2 = $composer3;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda84
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.WhyChooseUsSection$lambda$240(teacherName, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        static final Unit WhyChooseUsSection$lambda$239(String $teacherName, ColumnScope Card, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Intrinsics.checkNotNullParameter(Card, "$this$Card");
            ComposerKt.sourceInformation($composer, "C1479@66177L1385:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1596228096, $changed, -1, "com.example.sushikhasacademyapp.WhyChooseUsSection.<anonymous> (MainActivity.kt:1479)");
                }
                Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16));
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((6 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i3 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 759930279, "C1482@66333L10,1483@66430L11,1480@66234L231,1486@66479L41,1488@66534L230,1493@66863L11,1493@66777L132,1495@66923L243,1500@67265L11,1500@67179L132,1502@67325L227:MainActivity.kt#t7vdsr");
                TextKt.m3072TextNvy7gAk("Why Learn with " + $teacherName + "?", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 0, 0, 131066);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), $composer, 6);
                FeatureItem(PersonKt.getPerson(Icons.INSTANCE.getDefault()), "1-on-1 Speaking Practice", "Daily individual speaking turns to dissolve stage fear and accent anxiety.", $composer, 432);
                Modifier modifierM821paddingVpY3zN4$default = PaddingKt.m821paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m7905constructorimpl(8), 1, null);
                long jM2237getOutlineVariant0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOutlineVariant();
                DividerKt.m2399HorizontalDivider9IZ8Weo(modifierM821paddingVpY3zN4$default, 0.0f, Color.m5095copywmQWz5c(jM2237getOutlineVariant0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM2237getOutlineVariant0d7_KjU) : 0.5f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM2237getOutlineVariant0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM2237getOutlineVariant0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM2237getOutlineVariant0d7_KjU) : 0.0f), $composer, 6, 2);
                FeatureItem(BookKt.getBook(Icons.INSTANCE.getDefault()), "Custom English Workbooks", "Handcrafted grammar guides, vocabulary lists, and idiom compilations by " + $teacherName + " Sir.", $composer, 48);
                Modifier modifierM821paddingVpY3zN4$default2 = PaddingKt.m821paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m7905constructorimpl(8), 1, null);
                long jM2237getOutlineVariant0d7_KjU2 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOutlineVariant();
                DividerKt.m2399HorizontalDivider9IZ8Weo(modifierM821paddingVpY3zN4$default2, 0.0f, Color.m5095copywmQWz5c(jM2237getOutlineVariant0d7_KjU2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM2237getOutlineVariant0d7_KjU2) : 0.5f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM2237getOutlineVariant0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM2237getOutlineVariant0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM2237getOutlineVariant0d7_KjU2) : 0.0f), $composer, 6, 2);
                FeatureItem(CheckCircleKt.getCheckCircle(Icons.INSTANCE.getDefault()), "Small Batch Size", "Ensuring personalized feedback on sentence construction and pronunciation.", $composer, 432);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        public static final void FeatureItem(final ImageVector icon, final String title, String description, Composer $composer, final int $changed) {
            final String str;
            Composer $composer2;
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(description, "description");
            Composer $composer3 = $composer.startRestartGroup(-384701092);
            ComposerKt.sourceInformation($composer3, "C(FeatureItem)N(icon,title,description)1513@67661L992:MainActivity.kt#t7vdsr");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer3.changed(icon) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer3.changed(title) ? 32 : 16;
            }
            if (($changed & 384) == 0) {
                $dirty |= $composer3.changed(description) ? 256 : 128;
            }
            int $dirty2 = $dirty;
            if (!$composer3.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
                str = description;
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-384701092, $dirty2, -1, "com.example.sushikhasacademyapp.FeatureItem (MainActivity.kt:1512)");
                }
                Alignment.Vertical top = Alignment.INSTANCE.getTop();
                ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), top, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifier);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function0 = constructor;
                    $composer3.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer3.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i3 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -718755462, "C1517@67818L11,1514@67710L446,1528@68166L40,1530@68216L431:MainActivity.kt#t7vdsr");
                Modifier modifierM264backgroundbw27NRU = BackgroundKt.m264backgroundbw27NRU(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(36)), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimaryContainer(), RoundedCornerShapeKt.getCircleShape());
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifierM264backgroundbw27NRU);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor2;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer3.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i6 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -2076943759, "C1523@68064L11,1520@67942L204:MainActivity.kt#t7vdsr");
                IconKt.m2520Iconww6aTOc(icon, (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(20)), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary(), $composer3, ($dirty2 & 14) | 432, 0);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), $composer3, 6);
                ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier2 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer3, modifier2);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor3);
                } else {
                    $composer3.useNode();
                }
                Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i9 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1872390211, "C1533@68311L10,1534@68407L11,1531@68237L205,1538@68535L10,1539@68595L11,1536@68455L182:MainActivity.kt#t7vdsr");
                $composer2 = $composer3;
                TextKt.m3072TextNvy7gAk(title, null, MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnSurface(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getBodyMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer3, ($dirty2 >> 3) & 14, 0, 131066);
                str = description;
                TextKt.m3072TextNvy7gAk(str, null, MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getBodySmall(), $composer3, ($dirty2 >> 6) & 14, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda109
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.FeatureItem$lambda$244(icon, title, str, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        public static final void FeaturedGoogleReviewCard(final Testimonial testimonial, final Function0<Unit> onOpenGoogleReviews, Composer $composer, final int $changed) {
            Intrinsics.checkNotNullParameter(testimonial, "testimonial");
            Intrinsics.checkNotNullParameter(onOpenGoogleReviews, "onOpenGoogleReviews");
            Composer $composer2 = $composer.startRestartGroup(164368139);
            ComposerKt.sourceInformation($composer2, "C(FeaturedGoogleReviewCard)N(testimonial,onOpenGoogleReviews)1556@69005L11,1555@68950L112,1559@69174L38,1560@69219L5844,1550@68773L6290:MainActivity.kt#t7vdsr");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer2.changed(testimonial) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer2.changedInstance(onOpenGoogleReviews) ? 32 : 16;
            }
            if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(164368139, $dirty, -1, "com.example.sushikhasacademyapp.FeaturedGoogleReviewCard (MainActivity.kt:1549)");
                }
                Modifier modifierM821paddingVpY3zN4$default = PaddingKt.m821paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(16), 0.0f, 2, null);
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(20));
                CardDefaults cardDefaults = CardDefaults.INSTANCE;
                long jM2239getPrimaryContainer0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getPrimaryContainer();
                CardColors cardColorsM2144cardColorsro_MJ88 = cardDefaults.m2144cardColorsro_MJ88(Color.m5095copywmQWz5c(jM2239getPrimaryContainer0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM2239getPrimaryContainer0d7_KjU) : 0.5f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM2239getPrimaryContainer0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM2239getPrimaryContainer0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM2239getPrimaryContainer0d7_KjU) : 0.0f), 0L, 0L, 0L, $composer2, CardDefaults.$stable << 12, 14);
                float fM7905constructorimpl = Dp.m7905constructorimpl((float) 1.5d);
                long jColor = ColorKt.Color(4279673674L);
                BorderStroke borderStrokeM291BorderStrokecXLIe8U = BorderStrokeKt.m291BorderStrokecXLIe8U(fM7905constructorimpl, Color.m5095copywmQWz5c(jColor, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jColor) : 0.6f, (14 & 2) != 0 ? Color.m5103getRedimpl(jColor) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jColor) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jColor) : 0.0f));
                CardElevation cardElevationM2145cardElevationaqJV_2Y = CardDefaults.INSTANCE.m2145cardElevationaqJV_2Y(Dp.m7905constructorimpl(2), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer2, (CardDefaults.$stable << 18) | 6, 62);
                $composer2 = $composer2;
                CardKt.Card(modifierM821paddingVpY3zN4$default, roundedCornerShapeM1127RoundedCornerShape0680j_4, cardColorsM2144cardColorsro_MJ88, cardElevationM2145cardElevationaqJV_2Y, borderStrokeM291BorderStrokecXLIe8U, ComposableLambdaKt.rememberComposableLambda(73136985, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda54
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return MainActivityKt.FeaturedGoogleReviewCard$lambda$253(testimonial, onOpenGoogleReviews, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer2, 54), $composer2, 221190, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda55
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.FeaturedGoogleReviewCard$lambda$254(testimonial, onOpenGoogleReviews, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        static final Unit FeaturedGoogleReviewCard$lambda$253(Testimonial $testimonial, Function0 $onOpenGoogleReviews, ColumnScope Card, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Function0<ComposeUiNode> function2;
            Function0<ComposeUiNode> function3;
            Function0<ComposeUiNode> function4;
            Function0<ComposeUiNode> function5;
            Function0<ComposeUiNode> function6;
            Function0<ComposeUiNode> function7;
            Intrinsics.checkNotNullParameter(Card, "$this$Card");
            ComposerKt.sourceInformation($composer, "C1561@69229L5828:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(73136985, $changed, -1, "com.example.sushikhasacademyapp.FeaturedGoogleReviewCard.<anonymous> (MainActivity.kt:1561)");
                }
                Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(18));
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((6 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i3 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1873288609, "C1562@69286L3145,1631@72445L41,1635@72596L10,1636@72694L11,1633@72500L229,1639@72743L41,1641@72798L2249:MainActivity.kt#t7vdsr");
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor2;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i6 = ((438 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1573282147, "C1567@69504L2343,1617@71865L552:MainActivity.kt#t7vdsr");
                Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor3;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                int i9 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1385093661, "C1568@69578L1078,1591@70678L39,1593@70739L1090:MainActivity.kt#t7vdsr");
                SurfaceKt.m2929SurfaceT9BRK9s(null, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(8)), ColorKt.Color(4293457385L), 0L, 0.0f, 0.0f, null, ComposableSingletons$MainActivityKt.INSTANCE.m8677getLambda$1392927456$app(), $composer, 12583296, 121);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
                SurfaceKt.m2929SurfaceT9BRK9s(null, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(8)), ColorKt.Color(4294965700L), 0L, 0.0f, 0.0f, null, ComposableSingletons$MainActivityKt.INSTANCE.m8694getLambda$716414199$app(), $composer, 12583296, 121);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.TextButton($onOpenGoogleReviews, null, false, null, null, null, null, PaddingKt.m813PaddingValuesYgX7TsA(Dp.m7905constructorimpl(8), Dp.m7905constructorimpl(2)), null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1483673058$app(), $composer, 817889280, 382);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), $composer, 6);
                TextKt.m3072TextNvy7gAk("\"" + $testimonial.getReview() + "\"", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), 0L, 0L, FontWeight.INSTANCE.getNormal(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 0, 0, 131066);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), $composer, 6);
                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.Horizontal spaceBetween2 = Arrangement.INSTANCE.getSpaceBetween();
                Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(spaceBetween2, centerVertically3, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default2);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i10 = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function3 = constructor4;
                    $composer.createNode(function3);
                } else {
                    function3 = constructor4;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl4 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i11 = (i10 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                int i12 = ((438 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -545813350, "C1646@73016L1785,1686@74922L10,1687@74987L11,1684@74819L214:MainActivity.kt#t7vdsr");
                Alignment.Vertical centerVertically4 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier2 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically4, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap5 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier($composer, modifier2);
                Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                int i13 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function4 = constructor5;
                    $composer.createNode(function4);
                } else {
                    function4 = constructor5;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl5 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl5, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                int i14 = (i13 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                int i15 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -990419646, "C1650@73234L11,1647@73090L573,1660@73685L40,1662@73747L1036:MainActivity.kt#t7vdsr");
                Modifier modifierM264backgroundbw27NRU = BackgroundKt.m264backgroundbw27NRU(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(34)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), RoundedCornerShapeKt.getCircleShape());
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap6 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier($composer, modifierM264backgroundbw27NRU);
                Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                int i16 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function5 = constructor6;
                    $composer.createNode(function5);
                } else {
                    function5 = constructor6;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl6 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl6, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl6, Integer.valueOf(iHashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                int i17 = (i16 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i18 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 837526853, "C1656@73558L10,1653@73385L256:MainActivity.kt#t7vdsr");
                TextKt.m3072TextNvy7gAk(StringsKt.take($testimonial.getStudentName(), 1), null, Color.INSTANCE.m5134getWhite0d7_KjU(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 384, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), $composer, 6);
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier3 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap7 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier($composer, modifier3);
                Function0<ComposeUiNode> constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                int i19 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function6 = constructor7;
                    $composer.createNode(function6);
                } else {
                    function6 = constructor7;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl7 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl7, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl7, Integer.valueOf(iHashCode7), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl7, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl7, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                int i20 = (i19 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i21 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 218310016, "C1663@73780L723,1678@74644L10,1679@74716L11,1676@74528L233:MainActivity.kt#t7vdsr");
                Alignment.Vertical centerVertically5 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier4 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy5 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically5, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap8 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier($composer, modifier4);
                Function0<ComposeUiNode> constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                int i22 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function7 = constructor8;
                    $composer.createNode(function7);
                } else {
                    function7 = constructor8;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl8 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl8, measurePolicyRowMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl8, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl8, Integer.valueOf(iHashCode8), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl8, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl8, modifierMaterializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
                int i23 = (i22 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance5 = RowScopeInstance.INSTANCE;
                int i24 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -272318446, "C1666@73986L10,1664@73862L210,1668@74101L39,1669@74169L308:MainActivity.kt#t7vdsr");
                TextKt.m3072TextNvy7gAk($testimonial.getStudentName(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 0, 0, 131070);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(4)), $composer, 6);
                IconKt.m2520Iconww6aTOc(VerifiedKt.getVerified(Icons.INSTANCE.getDefault()), "Verified Google Reviewer", SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(14)), ColorKt.Color(4282549748L), $composer, 3504, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                TextKt.m3072TextNvy7gAk($testimonial.getCourseTaken(), null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                TextKt.m3072TextNvy7gAk("📍 Konnagar, West Bengal", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 6, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        public static final void TestimonialCard(final Testimonial testimonial, Composer $composer, final int $changed) {
            Composer $composer2;
            long jM2256getSurfaceVariant0d7_KjU;
            BorderStroke borderStrokeM291BorderStrokecXLIe8U;
            Intrinsics.checkNotNullParameter(testimonial, "testimonial");
            Composer $composer3 = $composer.startRestartGroup(-1729820477);
            ComposerKt.sourceInformation($composer3, "C(TestimonialCard)N(testimonial)1699@75253L193,1703@75601L83,1704@75691L4498,1696@75131L5058:MainActivity.kt#t7vdsr");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer3.changed(testimonial) ? 4 : 2;
            }
            int $dirty2 = $dirty;
            if ($composer3.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1729820477, $dirty2, -1, "com.example.sushikhasacademyapp.TestimonialCard (MainActivity.kt:1695)");
                }
                Modifier modifierM870width3ABfNKs = SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(280));
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16));
                CardDefaults cardDefaults = CardDefaults.INSTANCE;
                if (testimonial.isTopGoogleReview()) {
                    $composer3.startReplaceGroup(-205125067);
                    ComposerKt.sourceInformation($composer3, "1700@75343L11");
                    long jM2239getPrimaryContainer0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimaryContainer();
                    jM2256getSurfaceVariant0d7_KjU = Color.m5095copywmQWz5c(jM2239getPrimaryContainer0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM2239getPrimaryContainer0d7_KjU) : 0.5f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM2239getPrimaryContainer0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM2239getPrimaryContainer0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM2239getPrimaryContainer0d7_KjU) : 0.0f);
                } else {
                    $composer3.startReplaceGroup(-205123471);
                    ComposerKt.sourceInformation($composer3, "1700@75410L11");
                    jM2256getSurfaceVariant0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurfaceVariant();
                }
                $composer3.endReplaceGroup();
                CardColors cardColorsM2144cardColorsro_MJ88 = cardDefaults.m2144cardColorsro_MJ88(jM2256getSurfaceVariant0d7_KjU, 0L, 0L, 0L, $composer3, CardDefaults.$stable << 12, 14);
                if (testimonial.isTopGoogleReview()) {
                    float fM7905constructorimpl = Dp.m7905constructorimpl(1);
                    long jColor = ColorKt.Color(4279673674L);
                    borderStrokeM291BorderStrokecXLIe8U = BorderStrokeKt.m291BorderStrokecXLIe8U(fM7905constructorimpl, Color.m5095copywmQWz5c(jColor, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jColor) : 0.5f, (14 & 2) != 0 ? Color.m5103getRedimpl(jColor) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jColor) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jColor) : 0.0f));
                } else {
                    borderStrokeM291BorderStrokecXLIe8U = null;
                }
                CardKt.Card(modifierM870width3ABfNKs, roundedCornerShapeM1127RoundedCornerShape0680j_4, cardColorsM2144cardColorsro_MJ88, CardDefaults.INSTANCE.m2145cardElevationaqJV_2Y(testimonial.isTopGoogleReview() ? Dp.m7905constructorimpl(3) : Dp.m7905constructorimpl(1), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, CardDefaults.$stable << 18, 62), borderStrokeM291BorderStrokecXLIe8U, ComposableLambdaKt.rememberComposableLambda(62804789, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda93
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return MainActivityKt.TestimonialCard$lambda$262(testimonial, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer3, 54), $composer3, 196614, 0);
                $composer2 = $composer3;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda94
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.TestimonialCard$lambda$263(testimonial, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        static final Unit TestimonialCard$lambda$262(Testimonial $testimonial, ColumnScope Card, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Function0<ComposeUiNode> function2;
            Function0<ComposeUiNode> function3;
            Function0<ComposeUiNode> function4;
            Function0<ComposeUiNode> function5;
            Intrinsics.checkNotNullParameter(Card, "$this$Card");
            ComposerKt.sourceInformation($composer, "C1705@75701L4482:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(62804789, $changed, -1, "com.example.sushikhasacademyapp.TestimonialCard.<anonymous> (MainActivity.kt:1705)");
                }
                Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16));
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((6 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i3 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1504866521, "C1732@76910L1785,1774@78709L41,1776@78764L766,1795@79544L40,1799@79694L10,1800@79754L11,1797@79598L198:MainActivity.kt#t7vdsr");
                if ($testimonial.isTopGoogleReview()) {
                    $composer.startReplaceGroup(1504798568);
                    ComposerKt.sourceInformation($composer, "1707@75811L1071");
                    SurfaceKt.m2929SurfaceT9BRK9s(PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, Dp.m7905constructorimpl(8), 7, null), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(6)), ColorKt.Color(4292672743L), 0L, 0.0f, 0.0f, null, ComposableSingletons$MainActivityKt.INSTANCE.m8682getLambda$1696391455$app(), $composer, 12583302, MenuKt.InTransitionDuration);
                } else {
                    $composer.startReplaceGroup(1429576727);
                }
                $composer.endReplaceGroup();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor2;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i6 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -581394899, "C1738@77142L11,1735@77010L529,1748@77557L40,1750@77615L1066:MainActivity.kt#t7vdsr");
                Modifier modifierM264backgroundbw27NRU = BackgroundKt.m264backgroundbw27NRU(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(38)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), RoundedCornerShapeKt.getCircleShape());
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierM264backgroundbw27NRU);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor3;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i9 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1728835486, "C1744@77442L10,1741@77281L240:MainActivity.kt#t7vdsr");
                TextKt.m3072TextNvy7gAk(StringsKt.take($testimonial.getStudentName(), 1), null, Color.INSTANCE.m5134getWhite0d7_KjU(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 384, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), $composer, 6);
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier2 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifier2);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i10 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function3 = constructor4;
                    $composer.createNode(function3);
                } else {
                    function3 = constructor4;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl4 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i11 = (i10 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i12 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 478149507, "C1751@77644L781,1768@78554L10,1769@78622L11,1766@78446L217:MainActivity.kt#t7vdsr");
                Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier3 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap5 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier($composer, modifier3);
                Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                int i13 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function4 = constructor5;
                    $composer.createNode(function4);
                } else {
                    function4 = constructor5;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl5 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl5, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                int i14 = (i13 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                int i15 = ((384 >> 6) & 112) | 6;
                Composer composer = $composer;
                ComposerKt.sourceInformationMarkerStart(composer, -1243945679, "C1754@77838L10,1752@77722L198:MainActivity.kt#t7vdsr");
                TextKt.m3072TextNvy7gAk($testimonial.getStudentName(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodyMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), composer, 0, 0, 131070);
                if ($testimonial.isTopGoogleReview()) {
                    composer.startReplaceGroup(-1243697742);
                    ComposerKt.sourceInformation(composer, "1757@78010L39,1758@78078L299");
                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(4)), composer, 6);
                    IconKt.m2520Iconww6aTOc(VerifiedKt.getVerified(Icons.INSTANCE.getDefault()), "Verified Review", SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(14)), ColorKt.Color(4282549748L), composer, 3504, 0);
                    composer = composer;
                } else {
                    composer.startReplaceGroup(-1321068007);
                }
                composer.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                TextKt.m3072TextNvy7gAk($testimonial.getCourseTaken(), null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), $composer, 6);
                Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier4 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap6 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier($composer, modifier4);
                Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                int i16 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function5 = constructor6;
                    $composer.createNode(function5);
                } else {
                    function5 = constructor6;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl6 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl6, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl6, Integer.valueOf(iHashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                int i17 = (i16 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                int i18 = ((384 >> 6) & 112) | 6;
                Composer composer2 = $composer;
                ComposerKt.sourceInformationMarkerStart(composer2, 1372058643, "C:MainActivity.kt#t7vdsr");
                composer2.startReplaceGroup(-94287749);
                ComposerKt.sourceInformation(composer2, "*1778@78883L242");
                int i19 = 0;
                for (int rating = $testimonial.getRating(); i19 < rating; rating = rating) {
                    Composer composer3 = composer2;
                    IconKt.m2520Iconww6aTOc(StarKt.getStar(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(14)), ColorKt.Color(4294948864L), composer3, 3504, 0);
                    composer2 = composer3;
                    i19++;
                }
                composer2.endReplaceGroup();
                if ($testimonial.isTopGoogleReview()) {
                    composer2.startReplaceGroup(1372405563);
                    ComposerKt.sourceInformation(composer2, "1786@79217L39,1789@79369L10,1787@79277L221");
                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer2, 6);
                    TextKt.m3072TextNvy7gAk("5.0 ★", null, ColorKt.Color(4290007817L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getLabelSmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), composer2, 390, 0, 131066);
                } else {
                    composer2.startReplaceGroup(1293833180);
                }
                composer2.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), $composer, 6);
                TextKt.m3072TextNvy7gAk("\"" + $testimonial.getReview() + "\"", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131066);
                if (StringsKt.isBlank($testimonial.getLocation())) {
                    $composer.startReplaceGroup(1429576727);
                } else {
                    $composer.startReplaceGroup(1508797971);
                    ComposerKt.sourceInformation($composer, "1804@79867L40,1807@80029L10,1808@80094L11,1805@79924L235");
                    SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
                    String str = "📍 " + $testimonial.getLocation();
                    TextStyle labelSmall = MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall();
                    long jM2231getOnSurfaceVariant0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant();
                    TextKt.m3072TextNvy7gAk(str, null, Color.m5095copywmQWz5c(jM2231getOnSurfaceVariant0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM2231getOnSurfaceVariant0d7_KjU) : 0.7f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM2231getOnSurfaceVariant0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM2231getOnSurfaceVariant0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM2231getOnSurfaceVariant0d7_KjU) : 0.0f), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, labelSmall, $composer, 0, 0, 131066);
                }
                $composer.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        public static final void ContactFooterCard(final ContactInfo contactInfo, final String teacherName, Composer $composer, final int $changed) {
            Intrinsics.checkNotNullParameter(contactInfo, "contactInfo");
            Intrinsics.checkNotNullParameter(teacherName, "teacherName");
            Composer $composer2 = $composer.startRestartGroup(1155007173);
            ComposerKt.sourceInformation($composer2, "C(ContactFooterCard)N(contactInfo,teacherName)1820@80317L7,1825@80492L11,1825@80450L71,1827@80571L7302,1821@80329L7544:MainActivity.kt#t7vdsr");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= ($changed & 8) == 0 ? $composer2.changed(contactInfo) : $composer2.changedInstance(contactInfo) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer2.changed(teacherName) ? 32 : 16;
            }
            if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1155007173, $dirty, -1, "com.example.sushikhasacademyapp.ContactFooterCard (MainActivity.kt:1819)");
                }
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final Context context = (Context) objConsume;
                CardKt.Card(PaddingKt.m819padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(16)), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16)), CardDefaults.INSTANCE.m2144cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getPrimaryContainer(), 0L, 0L, 0L, $composer2, CardDefaults.$stable << 12, 14), null, null, ComposableLambdaKt.rememberComposableLambda(25581715, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda115
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return MainActivityKt.ContactFooterCard$lambda$284(teacherName, contactInfo, context, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer2, 54), $composer2, 196614, 24);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda116
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.ContactFooterCard$lambda$285(contactInfo, teacherName, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        /* JADX WARN: Code duplicated, block: B:101:0x0c47  */
        /* JADX WARN: Code duplicated, block: B:47:0x05bf  */
        /* JADX WARN: Code duplicated, block: B:50:0x05cb  */
        /* JADX WARN: Code duplicated, block: B:51:0x05d1  */
        /* JADX WARN: Code duplicated, block: B:65:0x0831  */
        /* JADX WARN: Code duplicated, block: B:68:0x083d  */
        /* JADX WARN: Code duplicated, block: B:69:0x0843  */
        static final Unit ContactFooterCard$lambda$284(String $teacherName, final ContactInfo $contactInfo, final Context $context, ColumnScope Card, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            int i;
            Function0<ComposeUiNode> function1;
            Composer composer;
            boolean zChangedInstance;
            Object objRememberedValue;
            Alignment.Vertical vertical;
            Composer composer2;
            Function0<ComposeUiNode> constructor;
            Function0<ComposeUiNode> function2;
            Alignment.Vertical vertical2;
            Composer composer3;
            Function0<ComposeUiNode> constructor2;
            Function0<ComposeUiNode> function3;
            Function0<ComposeUiNode> function4;
            Composer composer4;
            Intrinsics.checkNotNullParameter(Card, "$this$Card");
            ComposerKt.sourceInformation($composer, "C1828@80581L7286:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(25581715, $changed, -1, "com.example.sushikhasacademyapp.ContactFooterCard.<anonymous> (MainActivity.kt:1828)");
                }
                Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(20));
                Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i2 = ((((390 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor3;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor3;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = (i2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i4 = ((390 >> 6) & 112) | 6;
                Composer composer5 = $composer;
                ComposerKt.sourceInformationMarkerStart(composer5, -1625856887, "C1834@80820L10,1835@80916L11,1832@80724L236,1839@81065L10,1840@81163L11,1837@80973L223,1843@81210L41,1938@86054L1803:MainActivity.kt#t7vdsr");
                TextKt.m3072TextNvy7gAk("Sushiksha English Academy", null, MaterialTheme.INSTANCE.getColorScheme(composer5, MaterialTheme.$stable).getOnPrimaryFixed(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer5, MaterialTheme.$stable).getTitleLarge(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 6, 0, 131066);
                TextKt.m3072TextNvy7gAk("Teacher: " + $teacherName, null, MaterialTheme.INSTANCE.getColorScheme(composer5, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer5, MaterialTheme.$stable).getBodyMedium(), 0L, 0L, FontWeight.INSTANCE.getMedium(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 0, 0, 131066);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), composer5, 6);
                if (StringsKt.isBlank($contactInfo.getAddress())) {
                    composer5.startReplaceGroup(-1706157211);
                } else {
                    composer5.startReplaceGroup(-1625442294);
                    ComposerKt.sourceInformation(composer5, "1846@81321L496,1855@81834L40,1857@81929L738,1872@82824L48,1856@81891L1320,1878@83228L40");
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart(composer5, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer5, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer5, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer5, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = composer5.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer5, modifier);
                    Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                    int i5 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer5, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer5.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer5.startReusableNode();
                    if (composer5.getInserting()) {
                        function4 = constructor4;
                        composer5.createNode(function4);
                    } else {
                        function4 = constructor4;
                        composer5.useNode();
                    }
                    Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl(composer5);
                    Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i6 = (i5 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer5, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    int i7 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer5, 722950577, "C1847@81474L11,1847@81395L99,1848@81515L39,1851@81679L10,1852@81747L11,1849@81575L224:MainActivity.kt#t7vdsr");
                    IconKt.m2520Iconww6aTOc(LocationOnKt.getLocationOn(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer5, MaterialTheme.$stable).getPrimary(), composer5, 48, 4);
                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer5, 6);
                    TextKt.m3072TextNvy7gAk($contactInfo.getAddress(), null, MaterialTheme.INSTANCE.getColorScheme(composer5, MaterialTheme.$stable).getOnPrimaryFixed(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer5, MaterialTheme.$stable).getBodySmall(), composer5, 0, 0, 131066);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    composer5.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), composer5, 6);
                    ComposerKt.sourceInformationMarkerStart(composer5, -1714982817, "CC(remember):MainActivity.kt#9igjgp");
                    boolean zChangedInstance2 = composer5.changedInstance($contactInfo) | composer5.changedInstance($context);
                    Object objRememberedValue2 = composer5.rememberedValue();
                    if (!zChangedInstance2) {
                        composer4 = composer5;
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        Composer composer6 = composer4;
                        ButtonKt.Button((Function0) objRememberedValue2, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(10)), ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279522093L), 0L, 0L, 0L, composer4, (ButtonDefaults.$stable << 12) | 6, 14), null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m8676getLambda$1248528462$app(), composer6, 805306416, 484);
                        composer5 = composer6;
                        SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer5, 6);
                    } else {
                        composer4 = composer5;
                    }
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda110
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.ContactFooterCard$lambda$284$lambda$283$lambda$267$lambda$266($contactInfo, $context);
                        }
                    };
                    composer5.updateRememberedValue(obj);
                    objRememberedValue2 = obj;
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    Composer composer7 = composer4;
                    ButtonKt.Button((Function0) objRememberedValue2, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(10)), ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279522093L), 0L, 0L, 0L, composer4, (ButtonDefaults.$stable << 12) | 6, 14), null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m8676getLambda$1248528462$app(), composer7, 805306416, 484);
                    composer5 = composer7;
                    SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer5, 6);
                }
                composer5.endReplaceGroup();
                if (StringsKt.isBlank($contactInfo.getPhone())) {
                    i = 384;
                    composer5.startReplaceGroup(-1706157211);
                } else {
                    composer5.startReplaceGroup(-1623456093);
                    ComposerKt.sourceInformation(composer5, "1885@83498L366,1882@83350L1033,1902@84400L40");
                    Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                    Modifier.Companion companion = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composer5, -1714932981, "CC(remember):MainActivity.kt#9igjgp");
                    boolean zChangedInstance3 = composer5.changedInstance($contactInfo) | composer5.changedInstance($context);
                    Composer composer8 = composer5;
                    i = 384;
                    Object objRememberedValue3 = composer8.rememberedValue();
                    if (!zChangedInstance3) {
                        vertical2 = centerVertically2;
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        Modifier modifierM819padding3ABfNKs2 = PaddingKt.m819padding3ABfNKs(ClickableKt.m300clickableoSLSa3U$default(companion, false, null, null, null, (Function0) objRememberedValue3, 15, null), Dp.m7905constructorimpl(4));
                        composer3 = composer5;
                        ComposerKt.sourceInformationMarkerStart(composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), vertical2, composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap3 = composer3.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer3, modifierM819padding3ABfNKs2);
                        constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        int i8 = ((((384 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer3.startReusableNode();
                        if (composer3.getInserting()) {
                            function3 = constructor2;
                            composer3.createNode(function3);
                        } else {
                            function3 = constructor2;
                            composer3.useNode();
                        }
                        Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl(composer3);
                        Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                        int i9 = (i8 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                        int i10 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer3, 1210343799, "C1894@84018L11,1894@83944L94,1895@84059L39,1898@84221L10,1899@84324L11,1896@84119L246:MainActivity.kt#t7vdsr");
                        IconKt.m2520Iconww6aTOc(PhoneKt.getPhone(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer3, MaterialTheme.$stable).getPrimary(), composer3, 48, 4);
                        SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer3, 6);
                        TextKt.m3072TextNvy7gAk($contactInfo.getPhone(), null, MaterialTheme.INSTANCE.getColorScheme(composer3, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer3, MaterialTheme.$stable).getBodySmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), composer3, 0, 0, 131066);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer5, 6);
                    } else {
                        vertical2 = centerVertically2;
                    }
                    Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda111
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.ContactFooterCard$lambda$284$lambda$283$lambda$270$lambda$269($contactInfo, $context);
                        }
                    };
                    composer8.updateRememberedValue(obj2);
                    objRememberedValue3 = obj2;
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    Modifier modifierM819padding3ABfNKs3 = PaddingKt.m819padding3ABfNKs(ClickableKt.m300clickableoSLSa3U$default(companion, false, null, null, null, (Function0) objRememberedValue3, 15, null), Dp.m7905constructorimpl(4));
                    composer3 = composer5;
                    ComposerKt.sourceInformationMarkerStart(composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), vertical2, composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                    CompositionLocalMap currentCompositionLocalMap4 = composer3.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer3, modifierM819padding3ABfNKs3);
                    constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i11 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        function3 = constructor2;
                        composer3.createNode(function3);
                    } else {
                        function3 = constructor2;
                        composer3.useNode();
                    }
                    Composer composerM4367constructorimpl4 = Updater.m4367constructorimpl(composer3);
                    Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                    int i12 = (i11 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                    int i13 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, 1210343799, "C1894@84018L11,1894@83944L94,1895@84059L39,1898@84221L10,1899@84324L11,1896@84119L246:MainActivity.kt#t7vdsr");
                    IconKt.m2520Iconww6aTOc(PhoneKt.getPhone(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer3, MaterialTheme.$stable).getPrimary(), composer3, 48, 4);
                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer3, 6);
                    TextKt.m3072TextNvy7gAk($contactInfo.getPhone(), null, MaterialTheme.INSTANCE.getColorScheme(composer3, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer3, MaterialTheme.$stable).getBodySmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), composer3, 0, 0, 131066);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer5, 6);
                }
                composer5.endReplaceGroup();
                if (StringsKt.isBlank($contactInfo.getEmail())) {
                    composer5.startReplaceGroup(-1706157211);
                } else {
                    composer5.startReplaceGroup(-1622293004);
                    ComposerKt.sourceInformation(composer5, "1909@84670L380,1906@84522L1047,1926@85586L41");
                    Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composer5, -1714895463, "CC(remember):MainActivity.kt#9igjgp");
                    boolean zChangedInstance4 = composer5.changedInstance($contactInfo) | composer5.changedInstance($context);
                    Composer composer9 = composer5;
                    Object objRememberedValue4 = composer9.rememberedValue();
                    if (!zChangedInstance4) {
                        vertical = centerVertically3;
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        Modifier modifierM819padding3ABfNKs4 = PaddingKt.m819padding3ABfNKs(ClickableKt.m300clickableoSLSa3U$default(companion2, false, null, null, null, (Function0) objRememberedValue4, 15, null), Dp.m7905constructorimpl(4));
                        composer2 = composer5;
                        ComposerKt.sourceInformationMarkerStart(composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), vertical, composer2, ((i >> 3) & 14) | ((i >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                        CompositionLocalMap currentCompositionLocalMap5 = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer2, modifierM819padding3ABfNKs4);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int i14 = ((((i << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            function2 = constructor;
                            composer2.createNode(function2);
                        } else {
                            function2 = constructor;
                            composer2.useNode();
                        }
                        Composer composerM4367constructorimpl5 = Updater.m4367constructorimpl(composer2);
                        Updater.m4375setimpl(composerM4367constructorimpl5, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl(composerM4367constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4371initimpl(composerM4367constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4373reconcileimpl(composerM4367constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4375setimpl(composerM4367constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                        int i15 = (i14 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                        int i16 = ((i >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer2, -1594054792, "C1918@85204L11,1918@85130L94,1919@85245L39,1922@85407L10,1923@85510L11,1920@85305L246:MainActivity.kt#t7vdsr");
                        IconKt.m2520Iconww6aTOc(EmailKt.getEmail(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getPrimary(), composer2, 48, 4);
                        SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer2, 6);
                        TextKt.m3072TextNvy7gAk($contactInfo.getEmail(), null, MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getBodySmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), composer2, 0, 0, 131066);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), composer5, 6);
                    } else {
                        vertical = centerVertically3;
                    }
                    Object obj3 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda112
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.ContactFooterCard$lambda$284$lambda$283$lambda$274$lambda$273($contactInfo, $context);
                        }
                    };
                    composer9.updateRememberedValue(obj3);
                    objRememberedValue4 = obj3;
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    Modifier modifierM819padding3ABfNKs5 = PaddingKt.m819padding3ABfNKs(ClickableKt.m300clickableoSLSa3U$default(companion2, false, null, null, null, (Function0) objRememberedValue4, 15, null), Dp.m7905constructorimpl(4));
                    composer2 = composer5;
                    ComposerKt.sourceInformationMarkerStart(composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy5 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), vertical, composer2, ((i >> 3) & 14) | ((i >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap6 = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composer2, modifierM819padding3ABfNKs5);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int i17 = ((((i << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        function2 = constructor;
                        composer2.createNode(function2);
                    } else {
                        function2 = constructor;
                        composer2.useNode();
                    }
                    Composer composerM4367constructorimpl6 = Updater.m4367constructorimpl(composer2);
                    Updater.m4375setimpl(composerM4367constructorimpl6, measurePolicyRowMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl6, Integer.valueOf(iHashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                    int i18 = (i17 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance5 = RowScopeInstance.INSTANCE;
                    int i19 = ((i >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer2, -1594054792, "C1918@85204L11,1918@85130L94,1919@85245L39,1922@85407L10,1923@85510L11,1920@85305L246:MainActivity.kt#t7vdsr");
                    IconKt.m2520Iconww6aTOc(EmailKt.getEmail(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getPrimary(), composer2, 48, 4);
                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer2, 6);
                    TextKt.m3072TextNvy7gAk($contactInfo.getEmail(), null, MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getBodySmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), composer2, 0, 0, 131066);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), composer5, 6);
                }
                composer5.endReplaceGroup();
                if (StringsKt.isBlank($contactInfo.getAddress()) && StringsKt.isBlank($contactInfo.getEmail())) {
                    composer5.startReplaceGroup(-1621111098);
                    ComposerKt.sourceInformation(composer5, "1932@85858L10,1933@85922L11,1930@85739L229,1935@85985L41");
                    TextKt.m3072TextNvy7gAk("Contact details synced from SQL database", null, MaterialTheme.INSTANCE.getColorScheme(composer5, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer5, MaterialTheme.$stable).getBodySmall(), composer5, 6, 0, 131066);
                    SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), composer5, 6);
                } else {
                    composer5.startReplaceGroup(-1706157211);
                }
                composer5.endReplaceGroup();
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.Horizontal spaceEvenly = Arrangement.INSTANCE.getSpaceEvenly();
                Composer composer10 = composer5;
                Composer composer11 = composer5;
                ComposerKt.sourceInformationMarkerStart(composer10, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy6 = RowKt.rowMeasurePolicy(spaceEvenly, Alignment.INSTANCE.getTop(), composer10, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer10, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer10, 0));
                CompositionLocalMap currentCompositionLocalMap7 = composer10.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composer10, modifierFillMaxWidth$default);
                Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                int i20 = ((((54 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer10, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer10.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer10.startReusableNode();
                if (composer10.getInserting()) {
                    function1 = constructor5;
                    composer10.createNode(function1);
                } else {
                    function1 = constructor5;
                    composer10.useNode();
                }
                Composer composerM4367constructorimpl7 = Updater.m4367constructorimpl(composer10);
                Updater.m4375setimpl(composerM4367constructorimpl7, measurePolicyRowMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl7, Integer.valueOf(iHashCode7), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl7, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl7, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                int i21 = (i20 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer10, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance6 = RowScopeInstance.INSTANCE;
                int i22 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer10, 1747783622, "C1943@86253L465,1942@86207L807,1960@87070L477,1959@87032L811:MainActivity.kt#t7vdsr");
                ComposerKt.sourceInformationMarkerStart(composer10, 333475082, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance5 = composer10.changedInstance($contactInfo) | composer10.changedInstance($context);
                Object objRememberedValue5 = composer10.rememberedValue();
                if (!zChangedInstance5) {
                    composer = composer10;
                    if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ButtonKt.OutlinedButton((Function0) objRememberedValue5, null, false, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(10)), null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1488165959$app(), composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_DRAWPATH);
                    Composer composer12 = composer;
                    ComposerKt.sourceInformationMarkerStart(composer12, 333501238, "CC(remember):MainActivity.kt#9igjgp");
                    zChangedInstance = composer12.changedInstance($contactInfo) | composer12.changedInstance($context);
                    objRememberedValue = composer12.rememberedValue();
                    if (!zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda114
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MainActivityKt.ContactFooterCard$lambda$284$lambda$283$lambda$282$lambda$281$lambda$280($contactInfo, $context);
                            }
                        };
                        composer12.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer12);
                    ButtonKt.Button((Function0) objRememberedValue, null, false, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(10)), null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$500765577$app(), composer12, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_DRAWPATH);
                    ComposerKt.sourceInformationMarkerEnd(composer12);
                    ComposerKt.sourceInformationMarkerEnd(composer10);
                    composer10.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer10);
                    ComposerKt.sourceInformationMarkerEnd(composer10);
                    ComposerKt.sourceInformationMarkerEnd(composer10);
                    ComposerKt.sourceInformationMarkerEnd(composer11);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composer = composer10;
                }
                objRememberedValue5 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda113
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivityKt.ContactFooterCard$lambda$284$lambda$283$lambda$282$lambda$278$lambda$277($contactInfo, $context);
                    }
                };
                composer10.updateRememberedValue(objRememberedValue5);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ButtonKt.OutlinedButton((Function0) objRememberedValue5, null, false, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(10)), null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1488165959$app(), composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_DRAWPATH);
                Composer composer13 = composer;
                ComposerKt.sourceInformationMarkerStart(composer13, 333501238, "CC(remember):MainActivity.kt#9igjgp");
                zChangedInstance = composer13.changedInstance($contactInfo) | composer13.changedInstance($context);
                objRememberedValue = composer13.rememberedValue();
                if (!zChangedInstance) {
                }
                objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda114
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivityKt.ContactFooterCard$lambda$284$lambda$283$lambda$282$lambda$281$lambda$280($contactInfo, $context);
                    }
                };
                composer13.updateRememberedValue(objRememberedValue);
                ComposerKt.sourceInformationMarkerEnd(composer13);
                ButtonKt.Button((Function0) objRememberedValue, null, false, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(10)), null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$500765577$app(), composer13, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_DRAWPATH);
                ComposerKt.sourceInformationMarkerEnd(composer13);
                ComposerKt.sourceInformationMarkerEnd(composer10);
                composer10.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer10);
                ComposerKt.sourceInformationMarkerEnd(composer10);
                ComposerKt.sourceInformationMarkerEnd(composer10);
                ComposerKt.sourceInformationMarkerEnd(composer11);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit ContactFooterCard$lambda$284$lambda$283$lambda$267$lambda$266(ContactInfo $contactInfo, Context $context) {
            String addressQuery = Uri.encode($contactInfo.getAddress());
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + addressQuery);
            Intent mapIntent = new Intent("android.intent.action.VIEW", gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            try {
                $context.startActivity(mapIntent);
            } catch (Exception e) {
                Intent webIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.google.com/maps/search/?api=1&query=" + addressQuery));
                $context.startActivity(webIntent);
            }
            return Unit.INSTANCE;
        }

        static final Unit ContactFooterCard$lambda$284$lambda$283$lambda$270$lambda$269(ContactInfo $contactInfo, Context $context) {
            String phone = $contactInfo.getPhone();
            if (StringsKt.isBlank(phone)) {
                phone = "+919836926092";
            }
            String phoneNum = phone;
            try {
                Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phoneNum));
                $context.startActivity(intent);
            } catch (Exception e) {
            }
            return Unit.INSTANCE;
        }

        static final Unit ContactFooterCard$lambda$284$lambda$283$lambda$274$lambda$273(ContactInfo $contactInfo, Context $context) {
            String email = $contactInfo.getEmail();
            if (StringsKt.isBlank(email)) {
                email = "sushiksha.konnagar@gmail.com";
            }
            String email2 = email;
            try {
                Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse(MailTo.MAILTO_SCHEME + email2));
                $context.startActivity(intent);
            } catch (Exception e) {
            }
            return Unit.INSTANCE;
        }

        static final Unit ContactFooterCard$lambda$284$lambda$283$lambda$282$lambda$278$lambda$277(ContactInfo $contactInfo, Context $context) {
            String phone = $contactInfo.getPhone();
            if (StringsKt.isBlank(phone)) {
                phone = "+919836926092";
            }
            String phoneNum = phone;
            try {
                Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phoneNum));
                $context.startActivity(intent);
            } catch (Exception e) {
                Toast.makeText($context, "Call: " + phoneNum, 0).show();
            }
            return Unit.INSTANCE;
        }

        static final Unit ContactFooterCard$lambda$284$lambda$283$lambda$282$lambda$281$lambda$280(ContactInfo $contactInfo, Context $context) {
            String email = $contactInfo.getEmail();
            if (StringsKt.isBlank(email)) {
                email = "sushiksha.konnagar@gmail.com";
            }
            String email2 = email;
            try {
                Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse(MailTo.MAILTO_SCHEME + email2));
                $context.startActivity(intent);
            } catch (Exception e) {
                Toast.makeText($context, "Email: " + email2, 0).show();
            }
            return Unit.INSTANCE;
        }

        public static final void InquiryDialog(final String initialCourse, final String teacherName, final Function0<Unit> onDismiss, final Function3<? super String, ? super String, ? super String, Unit> onSubmit, Composer $composer, final int $changed) {
            Composer $composer2;
            Intrinsics.checkNotNullParameter(initialCourse, "initialCourse");
            Intrinsics.checkNotNullParameter(teacherName, "teacherName");
            Intrinsics.checkNotNullParameter(onDismiss, "onDismiss");
            Intrinsics.checkNotNullParameter(onSubmit, "onSubmit");
            Composer $composer3 = $composer.startRestartGroup(1754497483);
            ComposerKt.sourceInformation($composer3, "C(InquiryDialog)N(initialCourse,teacherName,onDismiss,onSubmit)1987@88082L31,1988@88133L31,1989@88187L42,2038@89967L387,2050@90380L102,1993@88302L471,2006@88790L1151,1991@88235L2253:MainActivity.kt#t7vdsr");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer3.changed(initialCourse) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer3.changed(teacherName) ? 32 : 16;
            }
            if (($changed & 384) == 0) {
                $dirty |= $composer3.changedInstance(onDismiss) ? 256 : 128;
            }
            if (($changed & 3072) == 0) {
                $dirty |= $composer3.changedInstance(onSubmit) ? 2048 : 1024;
            }
            if (!$composer3.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1754497483, $dirty, -1, "com.example.sushikhasacademyapp.InquiryDialog (MainActivity.kt:1986)");
                }
                ComposerKt.sourceInformationMarkerStart($composer3, -1850059574, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = $composer3.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default);
                    objRememberedValue = objMutableStateOf$default;
                }
                final MutableState studentName$delegate = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, -1850057942, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue2 = $composer3.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default2);
                    objRememberedValue2 = objMutableStateOf$default2;
                }
                final MutableState phoneNo$delegate = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, -1850056203, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue3 = $composer3.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(initialCourse, null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default3);
                    objRememberedValue3 = objMutableStateOf$default3;
                }
                final MutableState courseName$delegate = (MutableState) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2 = $composer3;
                AndroidAlertDialog_androidKt.m2066AlertDialogOix01E0(onDismiss, ComposableLambdaKt.rememberComposableLambda(-1259549053, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda104
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.InquiryDialog$lambda$297(onSubmit, studentName$delegate, phoneNo$delegate, courseName$delegate, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer3, 54), null, ComposableLambdaKt.rememberComposableLambda(-970729663, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda105
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.InquiryDialog$lambda$298(onDismiss, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer3, 54), null, ComposableLambdaKt.rememberComposableLambda(-681910273, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda106
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.InquiryDialog$lambda$300(teacherName, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(-537500578, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda107
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.InquiryDialog$lambda$308(studentName$delegate, phoneNo$delegate, courseName$delegate, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer3, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, $composer2, (($dirty >> 6) & 14) | 1772592, 0, 16276);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda108
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.InquiryDialog$lambda$309(initialCourse, teacherName, onDismiss, onSubmit, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        private static final String InquiryDialog$lambda$287(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String InquiryDialog$lambda$290(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String InquiryDialog$lambda$293(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        static final Unit InquiryDialog$lambda$300(String $teacherName, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            ComposerKt.sourceInformation($composer, "C1994@88316L447:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-681910273, $changed, -1, "com.example.sushikhasacademyapp.InquiryDialog.<anonymous> (MainActivity.kt:1994)");
                }
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i3 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1480730178, "C1997@88445L10,1995@88341L179,2001@88648L10,2002@88712L11,1999@88537L212:MainActivity.kt#t7vdsr");
                TextKt.m3072TextNvy7gAk("Academy Admission Inquiry", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 6, 0, 131070);
                TextKt.m3072TextNvy7gAk("Sushiksha Academy • " + $teacherName, null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit InquiryDialog$lambda$308(final MutableState $studentName$delegate, final MutableState $phoneNo$delegate, final MutableState $courseName$delegate, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            ComposerKt.sourceInformation($composer, "C2007@88804L1127:MainActivity.kt#t7vdsr");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-537500578, $changed, -1, "com.example.sushikhasacademyapp.InquiryDialog.<anonymous> (MainActivity.kt:2007)");
                }
                Modifier modifierM821paddingVpY3zN4$default = PaddingKt.m821paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m7905constructorimpl(8), 1, null);
                Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM821paddingVpY3zN4$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((54 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i3 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 32738211, "C2015@89125L20,2013@89029L283,2023@89422L16,2021@89330L275,2031@89718L19,2029@89623L294:MainActivity.kt#t7vdsr");
                String strInquiryDialog$lambda$287 = InquiryDialog$lambda$287($studentName$delegate);
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 832342268, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda71
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MainActivityKt.InquiryDialog$lambda$308$lambda$307$lambda$302$lambda$301($studentName$delegate, (String) obj);
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strInquiryDialog$lambda$287, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1803826126$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 12582912, 0, 8257464);
                String strInquiryDialog$lambda$290 = InquiryDialog$lambda$290($phoneNo$delegate);
                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 832351768, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda72
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return MainActivityKt.InquiryDialog$lambda$308$lambda$307$lambda$304$lambda$303($phoneNo$delegate, (String) obj2);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue2 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strInquiryDialog$lambda$290, (Function1<? super String, Unit>) objRememberedValue2, modifierFillMaxWidth$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$2137010551$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 12582912, 0, 8257464);
                String strInquiryDialog$lambda$293 = InquiryDialog$lambda$293($courseName$delegate);
                Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 832361243, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue3 = $composer.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda73
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return MainActivityKt.InquiryDialog$lambda$308$lambda$307$lambda$306$lambda$305($courseName$delegate, (String) obj3);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue3 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strInquiryDialog$lambda$293, (Function1<? super String, Unit>) objRememberedValue3, modifierFillMaxWidth$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$2002427862$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 12582912, 0, 8257464);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit InquiryDialog$lambda$308$lambda$307$lambda$302$lambda$301(MutableState $studentName$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $studentName$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit InquiryDialog$lambda$308$lambda$307$lambda$304$lambda$303(MutableState $phoneNo$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $phoneNo$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit InquiryDialog$lambda$308$lambda$307$lambda$306$lambda$305(MutableState $courseName$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $courseName$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit InquiryDialog$lambda$297(final Function3 $onSubmit, MutableState $studentName$delegate, MutableState $phoneNo$delegate, final MutableState $courseName$delegate, Composer $composer, int $changed) {
            final MutableState mutableState;
            final MutableState mutableState2;
            ComposerKt.sourceInformation($composer, "C2040@90015L184,2039@89981L363:MainActivity.kt#t7vdsr");
            boolean z = false;
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1259549053, $changed, -1, "com.example.sushikhasacademyapp.InquiryDialog.<anonymous> (MainActivity.kt:2039)");
                }
                ComposerKt.sourceInformationMarkerStart($composer, 754139259, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChanged = $composer.changed($onSubmit);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    mutableState = $studentName$delegate;
                    mutableState2 = $phoneNo$delegate;
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.InquiryDialog$lambda$297$lambda$296$lambda$295($onSubmit, mutableState, mutableState2, $courseName$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                } else {
                    mutableState = $studentName$delegate;
                    mutableState2 = $phoneNo$delegate;
                }
                Function0 function0 = (Function0) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (!StringsKt.isBlank(InquiryDialog$lambda$287(mutableState)) && !StringsKt.isBlank(InquiryDialog$lambda$290(mutableState2))) {
                    z = true;
                }
                ButtonKt.Button(function0, null, z, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m8683getLambda$1737540461$app(), $composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_PERCENT_X);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit InquiryDialog$lambda$297$lambda$296$lambda$295(Function3 $onSubmit, MutableState $studentName$delegate, MutableState $phoneNo$delegate, MutableState $courseName$delegate) {
            if (!StringsKt.isBlank(InquiryDialog$lambda$287($studentName$delegate)) && !StringsKt.isBlank(InquiryDialog$lambda$290($phoneNo$delegate))) {
                $onSubmit.invoke(InquiryDialog$lambda$287($studentName$delegate), InquiryDialog$lambda$290($phoneNo$delegate), InquiryDialog$lambda$293($courseName$delegate));
            }
            return Unit.INSTANCE;
        }

        static final Unit InquiryDialog$lambda$298(Function0 $onDismiss, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C2051@90394L78:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-970729663, $changed, -1, "com.example.sushikhasacademyapp.InquiryDialog.<anonymous> (MainActivity.kt:2051)");
                }
                ButtonKt.TextButton($onDismiss, null, false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m8684getLambda$1821505884$app(), $composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_POSITION_TYPE);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        public static final void LiveSketchBackground(Composer $composer, final int $changed) {
            Composer $composer2 = $composer.startRestartGroup(-735970817);
            ComposerKt.sourceInformation($composer2, "C(LiveSketchBackground)2060@90562L44,2061@90643L259,2071@90950L520,2071@90908L562:MainActivity.kt#t7vdsr");
            if (!$composer2.shouldExecute($changed != 0, $changed & 1)) {
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-735970817, $changed, -1, "com.example.sushikhasacademyapp.LiveSketchBackground (MainActivity.kt:2059)");
                }
                InfiniteTransition infiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition("sketch", $composer2, 6, 0);
                final State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.0f, 1000.0f, AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(15000, 0, EasingKt.getLinearEasing(), 2, null), RepeatMode.Restart, 0L, 4, null), TypedValues.CycleType.S_WAVE_PHASE, $composer2, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 1401663879, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChanged = $composer2.changed(stateAnimateFloat);
                Object objRememberedValue = $composer2.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return MainActivityKt.LiveSketchBackground$lambda$312$lambda$311(stateAnimateFloat, (DrawScope) obj2);
                        }
                    };
                    $composer2.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(modifierFillMaxSize$default, (Function1) objRememberedValue, $composer2, 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return MainActivityKt.LiveSketchBackground$lambda$313($changed, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }

        private static final float LiveSketchBackground$lambda$310(State<Float> state) {
            return ((Number) state.getValue()).floatValue();
        }

        static final Unit LiveSketchBackground$lambda$312$lambda$311(State $phase$delegate, DrawScope Canvas) {
            Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
            char c = ' ';
            float width = Float.intBitsToFloat((int) (Canvas.mo5671getSizeNHjbRc() >> 32));
            long j = 4294967295L;
            float height = Float.intBitsToFloat((int) (Canvas.mo5671getSizeNHjbRc() & 4294967295L));
            int i = 0;
            while (i < 8) {
                float cx = width * (((i * 0.15f) + (LiveSketchBackground$lambda$310($phase$delegate) * 4.0E-4f)) % 1.0f);
                float cy = height * ((i * 0.2f) + (((float) Math.sin((LiveSketchBackground$lambda$310($phase$delegate) + (i * 80)) * 0.004f)) * 0.12f) + 0.5f);
                float radius = 30.0f + (i * 20.0f);
                long jM5134getWhite0d7_KjU = Color.INSTANCE.m5134getWhite0d7_KjU();
                char c2 = c;
                long j2 = j;
                DrawScope.m5652drawCircleVaOC9Bg$default(Canvas, Color.m5095copywmQWz5c(jM5134getWhite0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5134getWhite0d7_KjU) : 0.09f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM5134getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5134getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5134getWhite0d7_KjU) : 0.0f), radius, Offset.m4844constructorimpl((((long) Float.floatToRawIntBits(cx)) << c2) | (((long) Float.floatToRawIntBits(cy)) & j2)), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
                i++;
                width = width;
                c = c2;
                j = j2;
            }
            return Unit.INSTANCE;
        }

        public static /* synthetic */ void saveUserToFirestore$default(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z, int i, Object obj) {
            boolean z2;
            if ((i & 256) == 0) {
                z2 = z;
            } else {
                z2 = false;
            }
            saveUserToFirestore(context, str, str2, str3, str4, str5, str6, str7, z2);
        }

        /* JADX WARN: Code duplicated, block: B:20:0x0084 A[Catch: Exception -> 0x0193, TryCatch #0 {Exception -> 0x0193, blocks: (B:6:0x0043, B:10:0x0057, B:12:0x0067, B:14:0x0071, B:17:0x007d, B:20:0x0084, B:21:0x008e, B:23:0x00af, B:27:0x00bb, B:29:0x00d0, B:33:0x00de, B:35:0x00ef, B:39:0x00fb, B:41:0x010c, B:46:0x0118, B:48:0x0140, B:49:0x017c), top: B:53:0x0043 }] */
        public static final void saveUserToFirestore(Context context, String uid, String username, String email, String dob, String studentClass, String school, String exam, boolean isNewUser) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(uid, "uid");
            Intrinsics.checkNotNullParameter(username, "username");
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(dob, "dob");
            Intrinsics.checkNotNullParameter(studentClass, "studentClass");
            Intrinsics.checkNotNullParameter(school, "school");
            Intrinsics.checkNotNullParameter(exam, "exam");
            if (StringsKt.isBlank(uid)) {
                return;
            }
            try {
                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                Intrinsics.checkNotNullExpressionValue(firestore, "getInstance(...)");
                String photoPath = ProfilePrefs.INSTANCE.getProfileImagePath(context);
                String str = "";
                if (photoPath == null) {
                    photoPath = "";
                }
                Map<String, String> userDetails = ProfilePrefs.INSTANCE.getUserDetails(context);
                String strSubstringBefore$default = username;
                if (StringsKt.isBlank(strSubstringBefore$default)) {
                    String str2 = userDetails.get(HintConstants.AUTOFILL_HINT_USERNAME);
                    if (str2 != null) {
                        String str3 = str2;
                        if (StringsKt.isBlank(str3)) {
                            str3 = null;
                        }
                        String str4 = str3;
                        if (str4 != null) {
                            strSubstringBefore$default = str4;
                        } else {
                            strSubstringBefore$default = StringsKt.substringBefore$default(email, "@", (String) null, 2, (Object) null);
                        }
                    } else {
                        strSubstringBefore$default = StringsKt.substringBefore$default(email, "@", (String) null, 2, (Object) null);
                    }
                }
                String finalUsername = strSubstringBefore$default;
                Pair[] pairArr = new Pair[8];
                pairArr[0] = TuplesKt.to(HintConstants.AUTOFILL_HINT_USERNAME, finalUsername);
                pairArr[1] = TuplesKt.to("email", email);
                String str5 = dob;
                if (StringsKt.isBlank(str5)) {
                    String str6 = userDetails.get("dob");
                    str5 = str6 == null ? "" : str6;
                }
                pairArr[2] = TuplesKt.to("dob", str5);
                String str7 = studentClass;
                if (StringsKt.isBlank(str7)) {
                    String str8 = userDetails.get("class");
                    str7 = str8 == null ? "" : str8;
                }
                pairArr[3] = TuplesKt.to("classLevel", str7);
                String str9 = school;
                if (StringsKt.isBlank(str9)) {
                    String str10 = userDetails.get("school");
                    str9 = str10 == null ? "" : str10;
                }
                pairArr[4] = TuplesKt.to("school", str9);
                String str11 = exam;
                if (StringsKt.isBlank(str11)) {
                    String str12 = userDetails.get("exam");
                    if (str12 != null) {
                        str = str12;
                    }
                    str11 = str;
                }
                pairArr[5] = TuplesKt.to("exam", str11);
                pairArr[6] = TuplesKt.to("photo", photoPath);
                pairArr[7] = TuplesKt.to("updatedAt", Timestamp.INSTANCE.now());
                HashMap userData = MapsKt.hashMapOf(pairArr);
                if (isNewUser) {
                    userData.put("createdAt", Timestamp.INSTANCE.now());
                    userData.put("isVerified", true);
                    userData.put("enrolledBatches", CollectionsKt.emptyList());
                    userData.put("enrolledCourses", CollectionsKt.emptyList());
                    userData.put("role", "student");
                }
                Intrinsics.checkNotNull(firestore.collection(Account.USERS).document(uid).set(userData, SetOptions.merge()));
            } catch (Exception e) {
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static final void LoginScreen(Function2<? super Boolean, ? super String, Unit> function2, Composer $composer, final int $changed) {
            Composer $composer2;
            char c;
            char c2;
            MutableState profileUri$delegate;
            Composer $composer3;
            int i;
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Function0<ComposeUiNode> function3;
            Function0<ComposeUiNode> function4;
            Function0<ComposeUiNode> function5;
            final Function2<? super Boolean, ? super String, Unit> onLoginSuccess = function2;
            Intrinsics.checkNotNullParameter(onLoginSuccess, "onLoginSuccess");
            Composer $composer4 = $composer.startRestartGroup(1763649636);
            ComposerKt.sourceInformation($composer4, "C(LoginScreen)N(onLoginSuccess)2136@93193L7,2137@93216L39,2138@93276L34,2140@93329L31,2141@93381L31,2142@93440L34,2145@93535L31,2146@93582L31,2147@93640L34,2155@94017L34,2156@94076L31,2166@94353L34,2167@94412L53,2169@94485L31,2170@94539L39,2172@94604L31,2173@94657L34,2177@94816L86,2175@94719L183,2204@95722L30609:MainActivity.kt#t7vdsr");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer4.changedInstance(onLoginSuccess) ? 4 : 2;
            }
            if ($composer4.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1763649636, $dirty, -1, "com.example.sushikhasacademyapp.LoginScreen (MainActivity.kt:2135)");
                }
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer4.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                final Context context = (Context) objConsume;
                ComposerKt.sourceInformationMarkerStart($composer4, -1644775637, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = $composer4.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object firebaseAuth = FirebaseAuth.getInstance();
                    $composer4.updateRememberedValue(firebaseAuth);
                    objRememberedValue = firebaseAuth;
                }
                final FirebaseAuth auth = (FirebaseAuth) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                Intrinsics.checkNotNull(auth);
                ComposerKt.sourceInformationMarkerStart($composer4, -1644773722, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue2 = $composer4.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    c = 4;
                    Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer4.updateRememberedValue(objMutableStateOf$default);
                    objRememberedValue2 = objMutableStateOf$default;
                } else {
                    c = 4;
                }
                final MutableState isSignUp$delegate = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, -1644772029, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue3 = $composer4.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                    $composer4.updateRememberedValue(objMutableStateOf$default2);
                    objRememberedValue3 = objMutableStateOf$default2;
                }
                final MutableState email$delegate = (MutableState) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, -1644770365, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue4 = $composer4.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                    $composer4.updateRememberedValue(objMutableStateOf$default3);
                    objRememberedValue4 = objMutableStateOf$default3;
                }
                final MutableState password$delegate = (MutableState) objRememberedValue4;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, -1644768474, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue5 = $composer4.rememberedValue();
                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    c2 = 1;
                    Object objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer4.updateRememberedValue(objMutableStateOf$default4);
                    objRememberedValue5 = objMutableStateOf$default4;
                } else {
                    c2 = 1;
                }
                final MutableState passwordVisible$delegate = (MutableState) objRememberedValue5;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, -1644765437, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue6 = $composer4.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                    $composer4.updateRememberedValue(objMutableStateOf$default5);
                    objRememberedValue6 = objMutableStateOf$default5;
                }
                final MutableState username$delegate = (MutableState) objRememberedValue6;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, -1644763933, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue7 = $composer4.rememberedValue();
                if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                    $composer4.updateRememberedValue(objMutableStateOf$default6);
                    objRememberedValue7 = objMutableStateOf$default6;
                }
                final MutableState dob$delegate = (MutableState) objRememberedValue7;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, -1644762074, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue8 = $composer4.rememberedValue();
                if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer4.updateRememberedValue(objMutableStateOf$default7);
                    objRememberedValue8 = objMutableStateOf$default7;
                }
                final MutableState showDatePicker$delegate = (MutableState) objRememberedValue8;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                String[] strArr = new String[16];
                strArr[0] = "Class 1";
                strArr[c2] = "Class 2";
                strArr[2] = "Class 3";
                strArr[3] = "Class 4";
                strArr[c] = "Class 5";
                strArr[5] = "Class 6";
                strArr[6] = "Class 7";
                strArr[7] = "Class 8";
                strArr[8] = "Class 9";
                strArr[9] = "Class 10";
                strArr[10] = "Class 11";
                strArr[11] = "Class 12";
                strArr[12] = "Undergraduate / College";
                strArr[13] = "Post Graduate";
                strArr[14] = "Spoken English & Fluency Program";
                strArr[15] = "Advanced Professional English";
                final List classOptions = CollectionsKt.listOf((Object[]) strArr);
                ComposerKt.sourceInformationMarkerStart($composer4, -1644750010, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue9 = $composer4.rememberedValue();
                if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer4.updateRememberedValue(objMutableStateOf$default8);
                    objRememberedValue9 = objMutableStateOf$default8;
                }
                final MutableState expandedClass$delegate = (MutableState) objRememberedValue9;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, -1644748125, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue10 = $composer4.rememberedValue();
                if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                    $composer4.updateRememberedValue(objMutableStateOf$default9);
                    objRememberedValue10 = objMutableStateOf$default9;
                }
                final MutableState studentClass$delegate = (MutableState) objRememberedValue10;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                String[] strArr2 = new String[6];
                strArr2[0] = "None / General English";
                strArr2[c2] = "IELTS Preparation";
                strArr2[2] = "TOEFL Preparation";
                strArr2[3] = "WBCS / UPSC English";
                strArr2[c] = "Banking & SSC English";
                strArr2[5] = "GRE / GMAT Verbal";
                final List examOptions = CollectionsKt.listOf((Object[]) strArr2);
                ComposerKt.sourceInformationMarkerStart($composer4, -1644739258, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue11 = $composer4.rememberedValue();
                if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default10 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer4.updateRememberedValue(objMutableStateOf$default10);
                    objRememberedValue11 = objMutableStateOf$default10;
                }
                final MutableState expandedExam$delegate = (MutableState) objRememberedValue11;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, -1644737351, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue12 = $composer4.rememberedValue();
                if (objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default11 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("None / General English", null, 2, null);
                    $composer4.updateRememberedValue(objMutableStateOf$default11);
                    objRememberedValue12 = objMutableStateOf$default11;
                }
                final MutableState selectedExam$delegate = (MutableState) objRememberedValue12;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, -1644735037, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue13 = $composer4.rememberedValue();
                if (objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default12 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                    $composer4.updateRememberedValue(objMutableStateOf$default12);
                    objRememberedValue13 = objMutableStateOf$default12;
                }
                final MutableState school$delegate = (MutableState) objRememberedValue13;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, -1644733301, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue14 = $composer4.rememberedValue();
                if (objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default13 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    $composer4.updateRememberedValue(objMutableStateOf$default13);
                    objRememberedValue14 = objMutableStateOf$default13;
                }
                final MutableState profileUri$delegate2 = (MutableState) objRememberedValue14;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, -1644731229, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue15 = $composer4.rememberedValue();
                if (objRememberedValue15 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default14 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                    $composer4.updateRememberedValue(objMutableStateOf$default14);
                    objRememberedValue15 = objMutableStateOf$default14;
                }
                final MutableState errorMessage$delegate = (MutableState) objRememberedValue15;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, -1644729530, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue16 = $composer4.rememberedValue();
                if (objRememberedValue16 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default15 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer4.updateRememberedValue(objMutableStateOf$default15);
                    objRememberedValue16 = objMutableStateOf$default15;
                }
                final MutableState isLoading$delegate = (MutableState) objRememberedValue16;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ActivityResultContracts.GetContent getContent = new ActivityResultContracts.GetContent();
                ComposerKt.sourceInformationMarkerStart($composer4, -1644724390, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue17 = $composer4.rememberedValue();
                if (objRememberedValue17 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda76
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return MainActivityKt.LoginScreen$lambda$367$lambda$366(profileUri$delegate2, (Uri) obj2);
                        }
                    };
                    $composer4.updateRememberedValue(obj);
                    objRememberedValue17 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer4);
                final ManagedActivityResultLauncher galleryLauncher = ActivityResultRegistryKt.rememberLauncherForActivityResult(getContent, (Function1) objRememberedValue17, $composer4, 48);
                if (LoginScreen$lambda$340(showDatePicker$delegate)) {
                    $composer4.startReplaceGroup(553284330);
                    ComposerKt.sourceInformation($composer4, "2184@94960L25,2186@95043L26,2187@95099L412,2196@95541L99,2199@95651L59,2185@94994L716");
                    profileUri$delegate = profileUri$delegate2;
                    i = 6;
                    final DatePickerState datePickerState = DatePickerKt.m2355rememberDatePickerStateEU0dCGE(null, null, null, 0, null, $composer4, 0, 31);
                    ComposerKt.sourceInformationMarkerStart($composer4, -1644717186, "CC(remember):MainActivity.kt#9igjgp");
                    Object objRememberedValue18 = $composer4.rememberedValue();
                    if (objRememberedValue18 == Composer.INSTANCE.getEmpty()) {
                        Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda77
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MainActivityKt.LoginScreen$lambda$369$lambda$368(showDatePicker$delegate);
                            }
                        };
                        $composer4.updateRememberedValue(obj2);
                        objRememberedValue18 = obj2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    boolean z = c2;
                    DatePickerDialog_androidKt.m2347DatePickerDialogGmEhDVc((Function0) objRememberedValue18, ComposableLambdaKt.rememberComposableLambda(1619242129, z, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda78
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj3, Object obj4) {
                            return MainActivityKt.LoginScreen$lambda$373(datePickerState, showDatePicker$delegate, dob$delegate, (Composer) obj3, ((Integer) obj4).intValue());
                        }
                    }, $composer4, 54), null, ComposableLambdaKt.rememberComposableLambda(2107289235, z, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda79
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj3, Object obj4) {
                            return MainActivityKt.LoginScreen$lambda$376(showDatePicker$delegate, (Composer) obj3, ((Integer) obj4).intValue());
                        }
                    }, $composer4, 54), null, 0.0f, null, null, ComposableLambdaKt.rememberComposableLambda(1485841192, z, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda80
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj3, Object obj4, Object obj5) {
                            return MainActivityKt.LoginScreen$lambda$377(datePickerState, (ColumnScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, $composer4, 54), $composer4, 100666422, 244);
                    $composer3 = $composer4;
                } else {
                    profileUri$delegate = profileUri$delegate2;
                    $composer3 = $composer4;
                    i = 6;
                    $composer3.startReplaceGroup(459090334);
                }
                $composer3.endReplaceGroup();
                Modifier modifierM265backgroundbw27NRU$default = BackgroundKt.m265backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, c2, null), ColorKt.Color(4279179050L), null, 2, null);
                int i2 = i;
                Composer composer = $composer3;
                ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM265backgroundbw27NRU$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i3 = ((((i2 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                    function0 = constructor;
                    composer.createNode(function0);
                } else {
                    function0 = constructor;
                    composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl(composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i4 = (i3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                int i5 = ((i2 >> 6) & 112) | 6;
                BoxScope boxScope = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer, -1705103531, "C2210@95921L3455,2305@99720L40,2306@99799L39,2307@99849L26476,2299@99456L26869:MainActivity.kt#t7vdsr");
                $composer2 = $composer3;
                Modifier modifierFillMaxHeight = SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.44f);
                int i6 = i;
                ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                int i7 = (i6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, modifierFillMaxHeight);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i8 = ((i7 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                    function1 = constructor2;
                    composer.createNode(function1);
                } else {
                    function1 = constructor2;
                    composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl(composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i9 = (i8 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i10 = ((i6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, 1140582049, "C2216@96085L47,2215@96052L238,2222@96343L160,2228@96558L375,2240@97021L2345:MainActivity.kt#t7vdsr");
                ImageKt.Image(PainterResources_androidKt.painterResource(R.drawable.coaching_image, composer, 0), (String) null, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), (Alignment) null, ContentScale.INSTANCE.getCrop(), 0.0f, (ColorFilter) null, composer, Painter.$stable | 25008, LocationRequestCompat.QUALITY_LOW_POWER);
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                long jColor = ColorKt.Color(4278355143L);
                BoxKt.Box(BackgroundKt.m265backgroundbw27NRU$default(modifierFillMaxSize$default, Color.m5095copywmQWz5c(jColor, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jColor) : 0.45f, (14 & 2) != 0 ? Color.m5103getRedimpl(jColor) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jColor) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jColor) : 0.0f), null, 2, null), composer, i);
                Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                Brush.Companion companion = Brush.INSTANCE;
                long jColor2 = ColorKt.Color(4278355143L);
                BoxKt.Box(BackgroundKt.background$default(modifierFillMaxSize$default2, Brush.Companion.m5052verticalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) new Color[]{Color.m5087boximpl(Color.INSTANCE.m5132getTransparent0d7_KjU()), Color.m5087boximpl(Color.m5095copywmQWz5c(jColor2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jColor2) : 0.7f, (14 & 2) != 0 ? Color.m5103getRedimpl(jColor2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jColor2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jColor2) : 0.0f)), Color.m5087boximpl(Color.INSTANCE.m5134getWhite0d7_KjU())}), 200.0f, 0.0f, 0, 12, (Object) null), null, 0.0f, 6, null), composer, 6);
                Modifier modifierM823paddingqDBjuR0$default = PaddingKt.m823paddingqDBjuR0$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m7905constructorimpl(36), 0.0f, 0.0f, 13, null);
                Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                Arrangement.Vertical top = Arrangement.INSTANCE.getTop();
                ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(top, centerHorizontally, composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer, modifierM823paddingqDBjuR0$default);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i11 = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                    function3 = constructor3;
                    composer.createNode(function3);
                } else {
                    function3 = constructor3;
                    composer.useNode();
                }
                Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl(composer);
                Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i12 = (i11 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i13 = ((438 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, 644235254, "C2247@97297L531,2261@97846L41,2264@97945L1407:MainActivity.kt#t7vdsr");
                Modifier modifierM865size3ABfNKs = SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(54));
                long jM5134getWhite0d7_KjU = Color.INSTANCE.m5134getWhite0d7_KjU();
                Modifier modifierM264backgroundbw27NRU = BackgroundKt.m264backgroundbw27NRU(modifierM865size3ABfNKs, Color.m5095copywmQWz5c(jM5134getWhite0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5134getWhite0d7_KjU) : 0.92f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM5134getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5134getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5134getWhite0d7_KjU) : 0.0f), RoundedCornerShapeKt.getCircleShape());
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer, modifierM264backgroundbw27NRU);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i14 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                    function4 = constructor4;
                    composer.createNode(function4);
                } else {
                    function4 = constructor4;
                    composer.useNode();
                }
                Composer composerM4367constructorimpl4 = Updater.m4367constructorimpl(composer);
                Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i15 = (i14 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i16 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, 1432765411, "C2253@97566L244:MainActivity.kt#t7vdsr");
                IconKt.m2520Iconww6aTOc(SchoolKt.getSchool(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(30)), ColorKt.Color(4278355143L), composer, 3504, 0);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), composer, 6);
                Alignment.Horizontal centerHorizontally2 = Alignment.INSTANCE.getCenterHorizontally();
                ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally2, composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                CompositionLocalMap currentCompositionLocalMap5 = composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer, modifier);
                Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                int i17 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                    function5 = constructor5;
                    composer.createNode(function5);
                } else {
                    function5 = constructor5;
                    composer.useNode();
                }
                Composer composerM4367constructorimpl5 = Updater.m4367constructorimpl(composer);
                Updater.m4375setimpl(composerM4367constructorimpl5, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                int i18 = (i17 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i19 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, 729491019, "C2267@98125L10,2265@98026L322,2273@98369L40,2276@98530L10,2274@98430L329,2282@98780L40,2283@98841L493:MainActivity.kt#t7vdsr");
                TextKt.m3072TextNvy7gAk("Learn Today.", null, Color.INSTANCE.m5134getWhite0d7_KjU(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getTitleLarge(), 0L, TextUnitKt.getSp(28), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer, 390, 0, 131066);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(2)), composer, 6);
                TextKt.m3072TextNvy7gAk("Lead Tomorrow", null, ColorKt.Color(4283096704L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getTitleLarge(), 0L, TextUnitKt.getSp(28), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer, 390, 0, 131066);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(4)), composer, 6);
                BoxKt.Box(BackgroundKt.background$default(SizeKt.m851height3ABfNKs(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(160)), Dp.m7905constructorimpl(3)), Brush.Companion.m5044horizontalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m5087boximpl(Color.INSTANCE.m5132getTransparent0d7_KjU()), Color.m5087boximpl(ColorKt.Color(4283096704L)), Color.m5087boximpl(Color.INSTANCE.m5132getTransparent0d7_KjU())}), 0.0f, 0.0f, 0, 14, (Object) null), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(2)), 0.0f, 4, null), composer, 0);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                onLoginSuccess = function2;
                final MutableState profileUri$delegate3 = profileUri$delegate;
                CardKt.Card(boxScope.align(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.64f), Alignment.INSTANCE.getBottomCenter()), RoundedCornerShapeKt.m1129RoundedCornerShapea9UjIt4$default(Dp.m7905constructorimpl(36), Dp.m7905constructorimpl(36), 0.0f, 0.0f, 12, null), CardDefaults.INSTANCE.m2144cardColorsro_MJ88(Color.INSTANCE.m5134getWhite0d7_KjU(), 0L, 0L, 0L, composer, (CardDefaults.$stable << 12) | 6, 14), CardDefaults.INSTANCE.m2145cardElevationaqJV_2Y(Dp.m7905constructorimpl(16), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer, (CardDefaults.$stable << 18) | 6, 62), null, ComposableLambdaKt.rememberComposableLambda(-562061476, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda81
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj3, Object obj4, Object obj5) {
                        return MainActivityKt.LoginScreen$lambda$468$lambda$467(auth, context, onLoginSuccess, isSignUp$delegate, galleryLauncher, profileUri$delegate3, username$delegate, email$delegate, password$delegate, passwordVisible$delegate, dob$delegate, showDatePicker$delegate, expandedClass$delegate, studentClass$delegate, classOptions, expandedExam$delegate, selectedExam$delegate, examOptions, school$delegate, errorMessage$delegate, isLoading$delegate, (ColumnScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, composer, 54), composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 16);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2 = $composer4;
                $composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda82
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        return MainActivityKt.LoginScreen$lambda$469(onLoginSuccess, $changed, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                });
            }
        }

        private static final boolean LoginScreen$lambda$322(MutableState<Boolean> mutableState) {
            return mutableState.getValue().booleanValue();
        }

        private static final void LoginScreen$lambda$323(MutableState<Boolean> mutableState, boolean z) {
            mutableState.setValue(Boolean.valueOf(z));
        }

        private static final String LoginScreen$lambda$325(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String LoginScreen$lambda$328(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final boolean LoginScreen$lambda$331(MutableState<Boolean> mutableState) {
            return mutableState.getValue().booleanValue();
        }

        private static final void LoginScreen$lambda$332(MutableState<Boolean> mutableState, boolean z) {
            mutableState.setValue(Boolean.valueOf(z));
        }

        private static final String LoginScreen$lambda$334(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String LoginScreen$lambda$337(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final boolean LoginScreen$lambda$340(MutableState<Boolean> mutableState) {
            return mutableState.getValue().booleanValue();
        }

        private static final void LoginScreen$lambda$341(MutableState<Boolean> mutableState, boolean z) {
            mutableState.setValue(Boolean.valueOf(z));
        }

        private static final boolean LoginScreen$lambda$343(MutableState<Boolean> mutableState) {
            return mutableState.getValue().booleanValue();
        }

        private static final void LoginScreen$lambda$344(MutableState<Boolean> mutableState, boolean z) {
            mutableState.setValue(Boolean.valueOf(z));
        }

        private static final String LoginScreen$lambda$346(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final boolean LoginScreen$lambda$349(MutableState<Boolean> mutableState) {
            return mutableState.getValue().booleanValue();
        }

        private static final void LoginScreen$lambda$350(MutableState<Boolean> mutableState, boolean z) {
            mutableState.setValue(Boolean.valueOf(z));
        }

        private static final String LoginScreen$lambda$352(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String LoginScreen$lambda$355(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final Uri LoginScreen$lambda$358(MutableState<Uri> mutableState) {
            return mutableState.getValue();
        }

        private static final String LoginScreen$lambda$361(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final boolean LoginScreen$lambda$364(MutableState<Boolean> mutableState) {
            return mutableState.getValue().booleanValue();
        }

        private static final void LoginScreen$lambda$365(MutableState<Boolean> mutableState, boolean z) {
            mutableState.setValue(Boolean.valueOf(z));
        }

        static final Unit LoginScreen$lambda$367$lambda$366(MutableState $profileUri$delegate, Uri uri) {
            if (uri != null) {
                $profileUri$delegate.setValue(uri);
            }
            return Unit.INSTANCE;
        }

        static final Unit LoginScreen$lambda$369$lambda$368(MutableState $showDatePicker$delegate) {
            LoginScreen$lambda$341($showDatePicker$delegate, false);
            return Unit.INSTANCE;
        }

        static final Unit LoginScreen$lambda$373(final DatePickerState $datePickerState, final MutableState $showDatePicker$delegate, final MutableState $dob$delegate, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C2188@95138L343,2188@95117L380:MainActivity.kt#t7vdsr");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1619242129, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous> (MainActivity.kt:2188)");
                }
                ComposerKt.sourceInformationMarkerStart($composer, 1313526408, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChanged = $composer.changed($datePickerState);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda64
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.LoginScreen$lambda$373$lambda$372$lambda$371($datePickerState, $showDatePicker$delegate, $dob$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$274545934$app(), $composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_POSITION_TYPE);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit LoginScreen$lambda$373$lambda$372$lambda$371(DatePickerState $datePickerState, MutableState $showDatePicker$delegate, MutableState $dob$delegate) {
            LoginScreen$lambda$341($showDatePicker$delegate, false);
            Long selectedDateMillis = $datePickerState.getSelectedDateMillis();
            if (selectedDateMillis != null) {
                String str = new SimpleDateFormat("dd / MM / yyyy", Locale.getDefault()).format(new Date(selectedDateMillis.longValue()));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                $dob$delegate.setValue(str);
            }
            return Unit.INSTANCE;
        }

        static final Unit LoginScreen$lambda$376(final MutableState $showDatePicker$delegate, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C2197@95580L26,2197@95559L67:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2107289235, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous> (MainActivity.kt:2197)");
                }
                ComposerKt.sourceInformationMarkerStart($composer, -2119607603, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda140
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.LoginScreen$lambda$376$lambda$375$lambda$374($showDatePicker$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$762593040$app(), $composer, 805306374, TypedValues.PositionType.TYPE_POSITION_TYPE);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit LoginScreen$lambda$376$lambda$375$lambda$374(MutableState $showDatePicker$delegate) {
            LoginScreen$lambda$341($showDatePicker$delegate, false);
            return Unit.INSTANCE;
        }

        static final Unit LoginScreen$lambda$377(DatePickerState $datePickerState, ColumnScope DatePickerDialog, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(DatePickerDialog, "$this$DatePickerDialog");
            ComposerKt.sourceInformation($composer, "C2200@95665L35:MainActivity.kt#t7vdsr");
            if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1485841192, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous> (MainActivity.kt:2200)");
                }
                DatePickerKt.DatePicker($datePickerState, null, null, null, null, null, false, null, $composer, 0, 254);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit LoginScreen$lambda$468$lambda$467(final FirebaseAuth $auth, final Context $context, final Function2 $onLoginSuccess, MutableState $isSignUp$delegate, final ManagedActivityResultLauncher $galleryLauncher, final MutableState $profileUri$delegate, final MutableState $username$delegate, final MutableState $email$delegate, final MutableState $password$delegate, final MutableState $passwordVisible$delegate, final MutableState $dob$delegate, final MutableState $showDatePicker$delegate, final MutableState $expandedClass$delegate, final MutableState $studentClass$delegate, final List $classOptions, final MutableState $expandedExam$delegate, final MutableState $selectedExam$delegate, final List $examOptions, final MutableState $school$delegate, final MutableState $errorMessage$delegate, final MutableState $isLoading$delegate, ColumnScope Card, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            PasswordVisualTransformation passwordVisualTransformation;
            Object obj;
            final MutableState mutableState;
            Function0<ComposeUiNode> function1;
            Composer composer;
            Intrinsics.checkNotNullParameter(Card, "$this$Card");
            ComposerKt.sourceInformation($composer, "C2312@100045L21,2308@99863L26452:MainActivity.kt#t7vdsr");
            if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-562061476, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous> (MainActivity.kt:2308)");
                }
                Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(PaddingKt.m820paddingVpY3zN4(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(24), Dp.m7905constructorimpl(20)), ScrollKt.rememberScrollState(0, $composer, 0, 1), false, null, false, 14, null);
                Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(14));
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, centerHorizontally, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierVerticalScroll$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                int i3 = ((432 >> 6) & 112) | 6;
                ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 2010847903, "C2316@100235L227,2325@100578L159,2323@100480L1256,2348@101754L40,2354@102047L2421,2350@101812L2656,2409@105015L294,2403@104615L14,2401@104525L858,2437@106547L294,2421@105544L17,2426@105954L442,2419@105451L1464,2450@107168L6788,2446@106933L7023,2578@114186L244,2574@113974L456,2612@115853L40,2616@116014L7018,2726@123253L48,2727@123320L1724,2615@115976L9068,2761@125062L40,2771@125564L54,2765@125230L43,2772@125637L664,2764@125184L1117:MainActivity.kt#t7vdsr");
                BoxKt.Box(BackgroundKt.m264backgroundbw27NRU(SizeKt.m851height3ABfNKs(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(40)), Dp.m7905constructorimpl(4)), ColorKt.Color(4291548641L), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(2))), $composer, 0);
                boolean zLoginScreen$lambda$322 = LoginScreen$lambda$322($isSignUp$delegate);
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Boolean boolValueOf = Boolean.valueOf(zLoginScreen$lambda$322);
                ComposerKt.sourceInformationMarkerStart($composer, -1320622191, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda117
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$385$lambda$384((AnimatedContentTransitionScope) obj3);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                AnimatedContentKt.AnimatedContent(boolValueOf, modifierFillMaxWidth$default, (Function1) objRememberedValue, null, "AuthTitleTransition", null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$2144619733$app(), $composer, 1597872, 40);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(2)), $composer, 6);
                AnimatedVisibilityKt.AnimatedVisibility(columnScope, LoginScreen$lambda$322($isSignUp$delegate), (Modifier) null, EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(300, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.expandVertically$default(AnimationSpecKt.tween$default(300, 0, null, 6, null), null, false, null, 14, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.shrinkVertically$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), null, false, null, 14, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(-296493782, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda120
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj3, Object obj4, Object obj5) {
                        return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$393($galleryLauncher, $profileUri$delegate, $username$delegate, (AnimatedVisibilityScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, $composer, 54), $composer, (i3 & 14) | 1600512, 18);
                String strLoginScreen$lambda$325 = LoginScreen$lambda$325($email$delegate);
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16));
                TextFieldColors textFieldColorsM2708colors0hiis_0 = OutlinedTextFieldDefaults.INSTANCE.m2708colors0hiis_0(0L, 0L, 0L, 0L, ColorKt.Color(4294507260L), ColorKt.Color(4294507260L), 0L, 0L, 0L, 0L, null, ColorKt.Color(4278355143L), ColorKt.Color(4293060848L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer, 221184, 432, 0, 0, 3072, 2147477455, 4095);
                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, -1320493152, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj3 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda121
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$395$lambda$394($email$delegate, (String) obj4);
                        }
                    };
                    $composer.updateRememberedValue(obj3);
                    objRememberedValue2 = obj3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strLoginScreen$lambda$325, (Function1<? super String, Unit>) objRememberedValue2, modifierFillMaxWidth$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$81595884$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1427350091$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.m8681getLambda$1521862998$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) roundedCornerShapeM1127RoundedCornerShape0680j_4, textFieldColorsM2708colors0hiis_0, $composer, 114819504, 12582912, 0, 1965624);
                String strLoginScreen$lambda$328 = LoginScreen$lambda$328($password$delegate);
                if (LoginScreen$lambda$331($passwordVisible$delegate)) {
                    passwordVisualTransformation = VisualTransformation.INSTANCE.getNone();
                } else {
                    passwordVisualTransformation = new PasswordVisualTransformation((char) 0, 1, null);
                }
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_5 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16));
                TextFieldColors textFieldColorsM2708colors0hiis_1 = OutlinedTextFieldDefaults.INSTANCE.m2708colors0hiis_0(0L, 0L, 0L, 0L, ColorKt.Color(4294507260L), ColorKt.Color(4294507260L), 0L, 0L, 0L, 0L, null, ColorKt.Color(4278355143L), ColorKt.Color(4293060848L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer, 221184, 432, 0, 0, 3072, 2147477455, 4095);
                VisualTransformation visualTransformation = passwordVisualTransformation;
                Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, -1320463421, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue3 = $composer.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    Object obj4 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda122
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj5) {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$397$lambda$396($password$delegate, (String) obj5);
                        }
                    };
                    $composer.updateRememberedValue(obj4);
                    objRememberedValue3 = obj4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strLoginScreen$lambda$328, (Function1<? super String, Unit>) objRememberedValue3, modifierFillMaxWidth$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1828687971$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.m8687getLambda$1981642494$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.m8680getLambda$1497005663$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1012368832, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda123
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj5, Object obj6) {
                        return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$401($passwordVisible$delegate, (Composer) obj5, ((Integer) obj6).intValue());
                    }
                }, $composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, visualTransformation, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) roundedCornerShapeM1127RoundedCornerShape0680j_5, textFieldColorsM2708colors0hiis_1, $composer, 920125872, 12582912, 0, 1948728);
                AnimatedVisibilityKt.AnimatedVisibility(columnScope, LoginScreen$lambda$322($isSignUp$delegate), (Modifier) null, EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(300, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.expandVertically$default(AnimationSpecKt.tween$default(300, 0, null, 6, null), null, false, null, 14, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.shrinkVertically$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), null, false, null, 14, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(-738228191, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda124
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj5, Object obj6, Object obj7) {
                        return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438($dob$delegate, $showDatePicker$delegate, $expandedClass$delegate, $studentClass$delegate, $classOptions, $expandedExam$delegate, $selectedExam$delegate, $examOptions, $school$delegate, (AnimatedVisibilityScope) obj5, (Composer) obj6, ((Integer) obj7).intValue());
                    }
                }, $composer, 54), $composer, (i3 & 14) | 1600512, 18);
                AnimatedVisibilityKt.AnimatedVisibility(columnScope, LoginScreen$lambda$361($errorMessage$delegate).length() > 0, (Modifier) null, EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null)), EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(-90000734, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda125
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj5, Object obj6, Object obj7) {
                        return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$439($errorMessage$delegate, (AnimatedVisibilityScope) obj5, (Composer) obj6, ((Integer) obj7).intValue());
                    }
                }, $composer, 54), $composer, (i3 & 14) | 1600512, 18);
                if (LoginScreen$lambda$322($isSignUp$delegate)) {
                    $composer.startReplaceGroup(1910605744);
                } else {
                    $composer.startReplaceGroup(2024196564);
                    ComposerKt.sourceInformation($composer, "2587@114485L1332");
                    Modifier modifierFillMaxWidth$default4 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Arrangement.Horizontal end = Arrangement.INSTANCE.getEnd();
                    ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(end, Alignment.INSTANCE.getTop(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default4);
                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i4 = ((((54 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function1 = constructor2;
                        $composer.createNode(function1);
                    } else {
                        function1 = constructor2;
                        $composer.useNode();
                    }
                    Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                    Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = (i4 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    int i6 = ((54 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -1803843195, "C2592@114712L905,2591@114662L1133:MainActivity.kt#t7vdsr");
                    ComposerKt.sourceInformationMarkerStart($composer, 218907546, "CC(remember):MainActivity.kt#9igjgp");
                    boolean zChangedInstance = $composer.changedInstance($auth) | $composer.changedInstance($context);
                    Object objRememberedValue4 = $composer.rememberedValue();
                    if (zChangedInstance) {
                        composer = $composer;
                    } else {
                        composer = $composer;
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ButtonKt.TextButton((Function0) objRememberedValue4, null, false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m8695getLambda$979317426$app(), composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_POSITION_TYPE);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                    }
                    objRememberedValue4 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda126
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$445$lambda$444$lambda$443($auth, $email$delegate, $errorMessage$delegate, $context);
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue4);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ButtonKt.TextButton((Function0) objRememberedValue4, null, false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m8695getLambda$979317426$app(), composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_POSITION_TYPE);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                }
                $composer.endReplaceGroup();
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(4)), $composer, 6);
                ComposerKt.sourceInformationMarkerStart($composer, -1320121380, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance2 = $composer.changedInstance($auth) | $composer.changedInstance($context) | $composer.changed($onLoginSuccess);
                Object objRememberedValue5 = $composer.rememberedValue();
                if (zChangedInstance2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    mutableState = $isSignUp$delegate;
                    obj = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda127
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458($auth, $email$delegate, $password$delegate, $errorMessage$delegate, $isLoading$delegate, mutableState, $context, $onLoginSuccess, $username$delegate, $dob$delegate, $studentClass$delegate, $school$delegate, $selectedExam$delegate, $profileUri$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                } else {
                    obj = objRememberedValue5;
                    mutableState = $isSignUp$delegate;
                }
                ComposerKt.sourceInformationMarkerEnd(
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0841: INVOKE (r0v58 ?? I:??[OBJECT, ARRAY]) STATIC call: androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(androidx.compose.runtime.Composer):void A[MD:(androidx.compose.runtime.Composer):void (m)] in method: com.example.sushikhasacademyapp.MainActivityKt.LoginScreen$lambda$468$lambda$467(com.google.firebase.auth.FirebaseAuth, android.content.Context, kotlin.jvm.functions.Function2, androidx.compose.runtime.MutableState, androidx.activity.compose.ManagedActivityResultLauncher, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, java.util.List, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, java.util.List, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit, file: classes7.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                    	at java.base/java.util.ArrayList.forEach(Unknown Source)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r0v58 ??
                    	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                    */
                /*
                    Method dump skipped, instruction units count: 2475
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.example.sushikhasacademyapp.MainActivityKt.LoginScreen$lambda$468$lambda$467(com.google.firebase.auth.FirebaseAuth, android.content.Context, kotlin.jvm.functions.Function2, androidx.compose.runtime.MutableState, androidx.activity.compose.ManagedActivityResultLauncher, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, java.util.List, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, java.util.List, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
            }

            static final ContentTransform LoginScreen$lambda$468$lambda$467$lambda$466$lambda$385$lambda$384(AnimatedContentTransitionScope AnimatedContent) {
                Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
                return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(250, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideInVertically$default(null, new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda51
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$385$lambda$384$lambda$382(((Integer) obj).intValue()));
                    }
                }, 1, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(RotationOptions.ROTATE_180, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideOutVertically$default(null, new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda52
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$385$lambda$384$lambda$383(((Integer) obj).intValue()));
                    }
                }, 1, null)));
            }

            static final int LoginScreen$lambda$468$lambda$467$lambda$466$lambda$385$lambda$384$lambda$382(int it) {
                return 20;
            }

            static final int LoginScreen$lambda$468$lambda$467$lambda$466$lambda$385$lambda$384$lambda$383(int it) {
                return -20;
            }

            /* JADX WARN: Code duplicated, block: B:22:0x0238  */
            /* JADX WARN: Code duplicated, block: B:25:0x0244  */
            /* JADX WARN: Code duplicated, block: B:26:0x024a  */
            /* JADX WARN: Code duplicated, block: B:29:0x02ca  */
            /* JADX WARN: Code duplicated, block: B:30:0x030d  */
            /* JADX WARN: Code duplicated, block: B:32:0x03a0  */
            /* JADX WARN: Code duplicated, block: B:35:0x03ac  */
            /* JADX WARN: Code duplicated, block: B:36:0x03b2  */
            /* JADX WARN: Code duplicated, block: B:40:0x056e  */
            /* JADX WARN: Code duplicated, block: B:41:0x057e  */
            /* JADX WARN: Code duplicated, block: B:44:0x05e8  */
            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$393(final ManagedActivityResultLauncher $galleryLauncher, MutableState $profileUri$delegate, final MutableState $username$delegate, AnimatedVisibilityScope AnimatedVisibility, Composer $composer, int $changed) {
                Function0<ComposeUiNode> function0;
                Composer composer;
                Composer composer2;
                Function0<ComposeUiNode> constructor;
                Function0<ComposeUiNode> function1;
                Composer composer3;
                Function0<ComposeUiNode> constructor2;
                Function0<ComposeUiNode> function2;
                Composer composer4;
                Object objRememberedValue;
                Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
                ComposerKt.sourceInformation($composer, "C2355@102069L2381:MainActivity.kt#t7vdsr");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-296493782, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2355)");
                }
                Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(14));
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor3;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor3;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                int i3 = ((48 >> 6) & 112) | 6;
                ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, -1594467374, "C2363@102527L37,2357@102203L1310,2389@104004L334,2384@103684L17,2382@103575L853:MainActivity.kt#t7vdsr");
                Modifier modifierAlign = columnScope.align(BackgroundKt.m265backgroundbw27NRU$default(ClipKt.clip(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(76)), RoundedCornerShapeKt.getCircleShape()), ColorKt.Color(4294047225L), null, 2, null), Alignment.INSTANCE.getCenterHorizontally());
                ComposerKt.sourceInformationMarkerStart($composer, 502763077, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance = $composer.changedInstance($galleryLauncher);
                Object objRememberedValue2 = $composer.rememberedValue();
                if (!zChangedInstance) {
                    composer = $composer;
                    if (objRememberedValue2 != Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    Modifier modifierM300clickableoSLSa3U$default = ClickableKt.m300clickableoSLSa3U$default(modifierAlign, false, null, null, null, (Function0) objRememberedValue2, 15, null);
                    Alignment center = Alignment.INSTANCE.getCenter();
                    composer2 = composer;
                    ComposerKt.sourceInformationMarkerStart(composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                    ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer2, modifierM300clickableoSLSa3U$default);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int i4 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        function1 = constructor;
                        composer2.createNode(function1);
                    } else {
                        function1 = constructor;
                        composer2.useNode();
                    }
                    Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl(composer2);
                    Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = (i4 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i6 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer2, 1875754972, "C:MainActivity.kt#t7vdsr");
                    if (LoginScreen$lambda$358($profileUri$delegate) != null) {
                        composer2.startReplaceGroup(1875765356);
                        ComposerKt.sourceInformation(composer2, "2367@102744L304");
                        composer3 = composer2;
                        SingletonAsyncImageKt.m8524AsyncImagegl8XCv8(LoginScreen$lambda$358($profileUri$delegate), null, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, false, null, composer3, 1573296, 0, 4024);
                        composer2.endReplaceGroup();
                    } else {
                        composer2.startReplaceGroup(1876137449);
                        ComposerKt.sourceInformation(composer2, "2374@103118L339");
                        Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                        composer3 = composer2;
                        ComposerKt.sourceInformationMarkerStart(composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        Modifier modifier2 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                        CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer2, modifier2);
                        constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        int i7 = ((((384 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            function2 = constructor2;
                            composer2.createNode(function2);
                        } else {
                            function2 = constructor2;
                            composer2.useNode();
                        }
                        Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl(composer2);
                        Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                        int i8 = (i7 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                        int i9 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer2, 1878946483, "C2375@103215L82,2376@103374L10,2376@103334L89:MainActivity.kt#t7vdsr");
                        IconKt.m2520Iconww6aTOc(CameraAltKt.getCameraAlt(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, ColorKt.Color(4279673674L), composer2, 3120, 4);
                        TextKt.m3072TextNvy7gAk("Add Photo", null, ColorKt.Color(4279673674L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getLabelSmall(), composer2, 390, 0, 131066);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        composer3.endReplaceGroup();
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    String strLoginScreen$lambda$334 = LoginScreen$lambda$334($username$delegate);
                    RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16));
                    TextFieldColors textFieldColorsM2708colors0hiis_0 = OutlinedTextFieldDefaults.INSTANCE.m2708colors0hiis_0(0L, 0L, 0L, 0L, ColorKt.Color(4294507260L), ColorKt.Color(4294507260L), 0L, 0L, 0L, 0L, null, ColorKt.Color(4278355143L), ColorKt.Color(4293060848L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 221184, 432, 0, 0, 3072, 2147477455, 4095);
                    composer4 = composer;
                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart(composer4, 502800081, "CC(remember):MainActivity.kt#9igjgp");
                    objRememberedValue = composer4.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda153
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$393$lambda$392$lambda$391$lambda$390($username$delegate, (String) obj2);
                            }
                        };
                        composer4.updateRememberedValue(obj);
                        objRememberedValue = obj;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    OutlinedTextFieldKt.OutlinedTextField(strLoginScreen$lambda$334, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1798851514$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1107947545$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) roundedCornerShapeM1127RoundedCornerShape0680j_4, textFieldColorsM2708colors0hiis_0, composer4, 14156208, 12582912, 0, 1965880);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    return Unit.INSTANCE;
                }
                composer = $composer;
                objRememberedValue2 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda152
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$393$lambda$392$lambda$387$lambda$386($galleryLauncher);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue2);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Modifier modifierM300clickableoSLSa3U$default2 = ClickableKt.m300clickableoSLSa3U$default(modifierAlign, false, null, null, null, (Function0) objRememberedValue2, 15, null);
                Alignment center2 = Alignment.INSTANCE.getCenter();
                composer2 = composer;
                ComposerKt.sourceInformationMarkerStart(composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                CompositionLocalMap currentCompositionLocalMap4 = composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer2, modifierM300clickableoSLSa3U$default2);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i10 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    function1 = constructor;
                    composer2.createNode(function1);
                } else {
                    function1 = constructor;
                    composer2.useNode();
                }
                Composer composerM4367constructorimpl4 = Updater.m4367constructorimpl(composer2);
                Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i11 = (i10 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i12 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer2, 1875754972, "C:MainActivity.kt#t7vdsr");
                if (LoginScreen$lambda$358($profileUri$delegate) != null) {
                    composer2.startReplaceGroup(1875765356);
                    ComposerKt.sourceInformation(composer2, "2367@102744L304");
                    composer3 = composer2;
                    SingletonAsyncImageKt.m8524AsyncImagegl8XCv8(LoginScreen$lambda$358($profileUri$delegate), null, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, false, null, composer3, 1573296, 0, 4024);
                    composer2.endReplaceGroup();
                } else {
                    composer2.startReplaceGroup(1876137449);
                    ComposerKt.sourceInformation(composer2, "2374@103118L339");
                    Alignment.Horizontal centerHorizontally2 = Alignment.INSTANCE.getCenterHorizontally();
                    composer3 = composer2;
                    ComposerKt.sourceInformationMarkerStart(composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier3 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyColumnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally2, composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap5 = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer2, modifier3);
                    constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i13 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        function2 = constructor2;
                        composer2.createNode(function2);
                    } else {
                        function2 = constructor2;
                        composer2.useNode();
                    }
                    Composer composerM4367constructorimpl5 = Updater.m4367constructorimpl(composer2);
                    Updater.m4375setimpl(composerM4367constructorimpl5, measurePolicyColumnMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                    int i14 = (i13 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                    int i15 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer2, 1878946483, "C2375@103215L82,2376@103374L10,2376@103334L89:MainActivity.kt#t7vdsr");
                    IconKt.m2520Iconww6aTOc(CameraAltKt.getCameraAlt(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, ColorKt.Color(4279673674L), composer2, 3120, 4);
                    TextKt.m3072TextNvy7gAk("Add Photo", null, ColorKt.Color(4279673674L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getLabelSmall(), composer2, 390, 0, 131066);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    composer3.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                String strLoginScreen$lambda$335 = LoginScreen$lambda$334($username$delegate);
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_5 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16));
                TextFieldColors textFieldColorsM2708colors0hiis_1 = OutlinedTextFieldDefaults.INSTANCE.m2708colors0hiis_0(0L, 0L, 0L, 0L, ColorKt.Color(4294507260L), ColorKt.Color(4294507260L), 0L, 0L, 0L, 0L, null, ColorKt.Color(4278355143L), ColorKt.Color(4293060848L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 221184, 432, 0, 0, 3072, 2147477455, 4095);
                composer4 = composer;
                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(composer4, 502800081, "CC(remember):MainActivity.kt#9igjgp");
                objRememberedValue = composer4.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda153
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$393$lambda$392$lambda$391$lambda$390($username$delegate, (String) obj3);
                        }
                    };
                    composer4.updateRememberedValue(obj2);
                    objRememberedValue = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd(composer4);
                OutlinedTextFieldKt.OutlinedTextField(strLoginScreen$lambda$335, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1798851514$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1107947545$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) roundedCornerShapeM1127RoundedCornerShape0680j_5, textFieldColorsM2708colors0hiis_1, composer4, 14156208, 12582912, 0, 1965880);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$393$lambda$392$lambda$387$lambda$386(ManagedActivityResultLauncher $galleryLauncher) {
                $galleryLauncher.launch("image/*");
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$393$lambda$392$lambda$391$lambda$390(MutableState $username$delegate, String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                $username$delegate.setValue(it);
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$395$lambda$394(MutableState $email$delegate, String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                $email$delegate.setValue(it);
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$397$lambda$396(MutableState $password$delegate, String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                $password$delegate.setValue(it);
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$401(final MutableState $passwordVisible$delegate, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C2427@106001L38,2427@106041L333,2427@105980L394:MainActivity.kt#t7vdsr");
                if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                    $composer.skipToGroupEnd();
                } else {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1012368832, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2427)");
                    }
                    ComposerKt.sourceInformationMarkerStart($composer, 2071130182, "CC(remember):MainActivity.kt#9igjgp");
                    Object objRememberedValue = $composer.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda16
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$401$lambda$399$lambda$398($passwordVisible$delegate);
                            }
                        };
                        $composer.updateRememberedValue(obj);
                        objRememberedValue = obj;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableLambdaKt.rememberComposableLambda(767482978, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda17
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$401$lambda$400($passwordVisible$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer, 54), $composer, 1572870, 62);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$401$lambda$399$lambda$398(MutableState $passwordVisible$delegate) {
                LoginScreen$lambda$332($passwordVisible$delegate, !LoginScreen$lambda$331($passwordVisible$delegate));
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$401$lambda$400(MutableState $passwordVisible$delegate, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C2428@106071L277:MainActivity.kt#t7vdsr");
                if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                    $composer.skipToGroupEnd();
                } else {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(767482978, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2428)");
                    }
                    IconKt.m2520Iconww6aTOc(LoginScreen$lambda$331($passwordVisible$delegate) ? VisibilityKt.getVisibility(Icons.INSTANCE.getDefault()) : VisibilityOffKt.getVisibilityOff(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, ColorKt.Color(4284773515L), $composer, 3120, 4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438(MutableState $dob$delegate, final MutableState $showDatePicker$delegate, final MutableState $expandedClass$delegate, final MutableState $studentClass$delegate, final List $classOptions, final MutableState $expandedExam$delegate, final MutableState $selectedExam$delegate, final List $examOptions, final MutableState $school$delegate, AnimatedVisibilityScope AnimatedVisibility, Composer $composer, int $changed) {
                Function0<ComposeUiNode> function0;
                Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
                ComposerKt.sourceInformation($composer, "C2451@107190L6748:MainActivity.kt#t7vdsr");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-738228191, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2451)");
                }
                Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(14));
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i3 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 903264787, "C2465@108051L334,2473@108526L25,2455@107435L3,2459@107657L266,2453@107331L1246,2479@108788L34,2481@108913L1918,2477@108663L2168,2519@111046L32,2521@111169L1827,2517@110922L2074,2563@113492L334,2558@113168L15,2556@113061L855:MainActivity.kt#t7vdsr");
                String strLoginScreen$lambda$337 = LoginScreen$lambda$337($dob$delegate);
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16));
                TextFieldColors textFieldColorsM2708colors0hiis_0 = OutlinedTextFieldDefaults.INSTANCE.m2708colors0hiis_0(0L, 0L, 0L, 0L, ColorKt.Color(4294507260L), ColorKt.Color(4294507260L), 0L, 0L, 0L, 0L, null, ColorKt.Color(4278355143L), ColorKt.Color(4293060848L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer, 221184, 432, 0, 0, 3072, 2147477455, 4095);
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, -1771946064, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda29
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$403$lambda$402($showDatePicker$delegate);
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier modifierM300clickableoSLSa3U$default = ClickableKt.m300clickableoSLSa3U$default(modifierFillMaxWidth$default, false, null, null, null, (Function0) objRememberedValue, 15, null);
                ComposerKt.sourceInformationMarkerStart($composer, -1771980998, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda30
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$405$lambda$404((String) obj2);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue2 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strLoginScreen$lambda$337, (Function1<? super String, Unit>) objRememberedValue2, modifierM300clickableoSLSa3U$default, false, true, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.m8678getLambda$1451060559$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$310156112$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-462377842, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda31
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$408($showDatePicker$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) roundedCornerShapeM1127RoundedCornerShape0680j_4, textFieldColorsM2708colors0hiis_0, $composer, 819486768, 0, 0, 2096424);
                boolean zLoginScreen$lambda$343 = LoginScreen$lambda$343($expandedClass$delegate);
                ComposerKt.sourceInformationMarkerStart($composer, -1771937671, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue3 = $composer.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda32
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$410$lambda$409($expandedClass$delegate, ((Boolean) obj2).booleanValue());
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ExposedDropdownMenuKt.ExposedDropdownMenuBox(zLoginScreen$lambda$343, (Function1) objRememberedValue3, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ComposableLambdaKt.rememberComposableLambda(-301181523, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda33
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$421($studentClass$delegate, $expandedClass$delegate, $classOptions, (ExposedDropdownMenuBoxScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, $composer, 54), $composer, 3504, 0);
                boolean zLoginScreen$lambda$349 = LoginScreen$lambda$349($expandedExam$delegate);
                ComposerKt.sourceInformationMarkerStart($composer, -1771865417, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue4 = $composer.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda34
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$423$lambda$422($expandedExam$delegate, ((Boolean) obj3).booleanValue());
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue4 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ExposedDropdownMenuKt.ExposedDropdownMenuBox(zLoginScreen$lambda$349, (Function1) objRememberedValue4, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ComposableLambdaKt.rememberComposableLambda(766564196, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda35
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj3, Object obj4, Object obj5) {
                        return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$434($selectedExam$delegate, $expandedExam$delegate, $examOptions, (ExposedDropdownMenuBoxScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, $composer, 54), $composer, 3504, 0);
                String strLoginScreen$lambda$355 = LoginScreen$lambda$355($school$delegate);
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_5 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16));
                TextFieldColors textFieldColorsM2708colors0hiis_1 = OutlinedTextFieldDefaults.INSTANCE.m2708colors0hiis_0(0L, 0L, 0L, 0L, ColorKt.Color(4294507260L), ColorKt.Color(4294507260L), 0L, 0L, 0L, 0L, null, ColorKt.Color(4278355143L), ColorKt.Color(4293060848L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer, 221184, 432, 0, 0, 3072, 2147477455, 4095);
                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, -1771797530, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue5 = $composer.rememberedValue();
                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    Object obj3 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda36
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$436$lambda$435($school$delegate, (String) obj4);
                        }
                    };
                    $composer.updateRememberedValue(obj3);
                    objRememberedValue5 = obj3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strLoginScreen$lambda$355, (Function1<? super String, Unit>) objRememberedValue5, modifierFillMaxWidth$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.m8693getLambda$460594328$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.m8679getLambda$148488121$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) roundedCornerShapeM1127RoundedCornerShape0680j_5, textFieldColorsM2708colors0hiis_1, $composer, 14156208, 12582912, 0, 1965880);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$405$lambda$404(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$408(final MutableState $showDatePicker$delegate, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C2460@107712L25,2460@107691L202:MainActivity.kt#t7vdsr");
                if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                    $composer.skipToGroupEnd();
                } else {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-462377842, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2460)");
                    }
                    ComposerKt.sourceInformationMarkerStart($composer, -1963472889, "CC(remember):MainActivity.kt#9igjgp");
                    Object objRememberedValue = $composer.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda144
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$408$lambda$407$lambda$406($showDatePicker$delegate);
                            }
                        };
                        $composer.updateRememberedValue(obj);
                        objRememberedValue = obj;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1170402992$app(), $composer, 1572870, 62);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$408$lambda$407$lambda$406(MutableState $showDatePicker$delegate) {
                LoginScreen$lambda$341($showDatePicker$delegate, true);
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$403$lambda$402(MutableState $showDatePicker$delegate) {
                LoginScreen$lambda$341($showDatePicker$delegate, true);
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$410$lambda$409(MutableState $expandedClass$delegate, boolean it) {
                LoginScreen$lambda$344($expandedClass$delegate, !LoginScreen$lambda$343($expandedClass$delegate));
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$421(final MutableState $studentClass$delegate, final MutableState $expandedClass$delegate, final List $classOptions, ExposedDropdownMenuBoxScope ExposedDropdownMenuBox, Composer $composer, int $changed) {
                Intrinsics.checkNotNullParameter(ExposedDropdownMenuBox, "$this$ExposedDropdownMenuBox");
                ComposerKt.sourceInformation($composer, "C2490@109538L354,2484@109064L2,2488@109332L70,2482@108943L1134,2502@110236L25,2503@110292L513,2500@110106L699:MainActivity.kt#t7vdsr");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= ($changed & 8) == 0 ? $composer.changed(ExposedDropdownMenuBox) : $composer.changedInstance(ExposedDropdownMenuBox) ? 4 : 2;
                }
                if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-301181523, $dirty, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2482)");
                    }
                    String strLoginScreen$lambda$346 = LoginScreen$lambda$346($studentClass$delegate);
                    RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16));
                    int $dirty2 = $dirty;
                    TextFieldColors textFieldColorsM2708colors0hiis_0 = OutlinedTextFieldDefaults.INSTANCE.m2708colors0hiis_0(0L, 0L, 0L, 0L, ColorKt.Color(4294507260L), ColorKt.Color(4294507260L), 0L, 0L, 0L, 0L, null, ColorKt.Color(4278355143L), ColorKt.Color(4293060848L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer, 221184, 432, 0, 0, 3072, 2147477455, 4095);
                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(ExposedDropdownMenuBox.menuAnchor(Modifier.INSTANCE), 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer, -1672629937, "CC(remember):MainActivity.kt#9igjgp");
                    Object objRememberedValue = $composer.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda45
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$421$lambda$412$lambda$411((String) obj2);
                            }
                        };
                        $composer.updateRememberedValue(obj);
                        objRememberedValue = obj;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    OutlinedTextFieldKt.OutlinedTextField(strLoginScreen$lambda$346, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, true, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.m8688getLambda$2045387321$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$2102651110$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1808793380, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda46
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$421$lambda$413($expandedClass$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) roundedCornerShapeM1127RoundedCornerShape0680j_4, textFieldColorsM2708colors0hiis_0, $composer, 819486768, 0, 0, 2096424);
                    boolean zLoginScreen$lambda$343 = LoginScreen$lambda$343($expandedClass$delegate);
                    ComposerKt.sourceInformationMarkerStart($composer, -1672592410, "CC(remember):MainActivity.kt#9igjgp");
                    Object objRememberedValue2 = $composer.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda47
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$421$lambda$415$lambda$414($expandedClass$delegate);
                            }
                        };
                        $composer.updateRememberedValue(obj2);
                        objRememberedValue2 = obj2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ExposedDropdownMenuBox.m2426ExposedDropdownMenuvNxi1II(zLoginScreen$lambda$343, (Function0) objRememberedValue2, null, null, false, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(2019133035, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda48
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj3, Object obj4, Object obj5) {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$421$lambda$420($classOptions, $studentClass$delegate, $expandedClass$delegate, (ColumnScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, $composer, 54), $composer, 48, (ExposedDropdownMenuBoxScope.$stable << 3) | 6 | (($dirty2 << 3) & 112), 1020);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer.skipToGroupEnd();
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$421$lambda$412$lambda$411(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$421$lambda$413(MutableState $expandedClass$delegate, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C2488@109362L38:MainActivity.kt#t7vdsr");
                if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1808793380, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2488)");
                    }
                    ExposedDropdownMenuDefaults.INSTANCE.TrailingIcon(LoginScreen$lambda$343($expandedClass$delegate), null, $composer, ExposedDropdownMenuDefaults.$stable << 6, 2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer.skipToGroupEnd();
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$421$lambda$415$lambda$414(MutableState $expandedClass$delegate) {
                LoginScreen$lambda$344($expandedClass$delegate, false);
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$421$lambda$420(List $classOptions, final MutableState $studentClass$delegate, final MutableState $expandedClass$delegate, ColumnScope ExposedDropdownMenu, Composer $composer, int $changed) {
                Composer composer = $composer;
                Intrinsics.checkNotNullParameter(ExposedDropdownMenu, "$this$ExposedDropdownMenu");
                ComposerKt.sourceInformation(composer, "C*2506@110460L16,2507@110528L175,2505@110395L346:MainActivity.kt#t7vdsr");
                if (!composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                    $composer.skipToGroupEnd();
                } else {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(2019133035, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2504)");
                    }
                    Iterator it = $classOptions.iterator();
                    while (it.hasNext()) {
                        final String str = (String) it.next();
                        ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-158806805, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda85
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$421$lambda$420$lambda$419$lambda$416(str, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        }, composer, 54);
                        ComposerKt.sourceInformationMarkerStart(composer, 1935570762, "CC(remember):MainActivity.kt#9igjgp");
                        boolean zChanged = composer.changed(str);
                        Object objRememberedValue = $composer.rememberedValue();
                        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda86
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$421$lambda$420$lambda$419$lambda$418$lambda$417(str, $studentClass$delegate, $expandedClass$delegate);
                                }
                            };
                            $composer.updateRememberedValue(obj);
                            objRememberedValue = obj;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        AndroidMenu_androidKt.DropdownMenuItem(composableLambdaRememberComposableLambda, (Function0) objRememberedValue, null, null, null, false, null, null, null, composer, 6, TypedValues.PositionType.TYPE_CURVE_FIT);
                        composer = $composer;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$421$lambda$420$lambda$419$lambda$416(String $option, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C2506@110462L12:MainActivity.kt#t7vdsr");
                if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-158806805, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2506)");
                    }
                    TextKt.m3072TextNvy7gAk($option, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer.skipToGroupEnd();
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$421$lambda$420$lambda$419$lambda$418$lambda$417(String $option, MutableState $studentClass$delegate, MutableState $expandedClass$delegate) {
                $studentClass$delegate.setValue($option);
                LoginScreen$lambda$344($expandedClass$delegate, false);
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$423$lambda$422(MutableState $expandedExam$delegate, boolean it) {
                LoginScreen$lambda$350($expandedExam$delegate, !LoginScreen$lambda$349($expandedExam$delegate));
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$434(final MutableState $selectedExam$delegate, final MutableState $expandedExam$delegate, final List $examOptions, ExposedDropdownMenuBoxScope ExposedDropdownMenuBox, Composer $composer, int $changed) {
                Intrinsics.checkNotNullParameter(ExposedDropdownMenuBox, "$this$ExposedDropdownMenuBox");
                ComposerKt.sourceInformation($composer, "C2529@111707L354,2524@111320L2,2527@111502L69,2522@111199L1047,2541@112404L24,2542@112459L511,2539@112275L695:MainActivity.kt#t7vdsr");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= ($changed & 8) == 0 ? $composer.changed(ExposedDropdownMenuBox) : $composer.changedInstance(ExposedDropdownMenuBox) ? 4 : 2;
                }
                if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(766564196, $dirty, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2522)");
                    }
                    String strLoginScreen$lambda$352 = LoginScreen$lambda$352($selectedExam$delegate);
                    RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16));
                    int $dirty2 = $dirty;
                    TextFieldColors textFieldColorsM2708colors0hiis_0 = OutlinedTextFieldDefaults.INSTANCE.m2708colors0hiis_0(0L, 0L, 0L, 0L, ColorKt.Color(4294507260L), ColorKt.Color(4294507260L), 0L, 0L, 0L, 0L, null, ColorKt.Color(4278355143L), ColorKt.Color(4293060848L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer, 221184, 432, 0, 0, 3072, 2147477455, 4095);
                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(ExposedDropdownMenuBox.menuAnchor(Modifier.INSTANCE), 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer, -2066177722, "CC(remember):MainActivity.kt#9igjgp");
                    Object objRememberedValue = $composer.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda95
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$434$lambda$425$lambda$424((String) obj2);
                            }
                        };
                        $composer.updateRememberedValue(obj);
                        objRememberedValue = obj;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    OutlinedTextFieldKt.OutlinedTextField(strLoginScreen$lambda$352, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, true, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.m8691getLambda$377962242$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1227882267, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda96
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$434$lambda$426($expandedExam$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) roundedCornerShapeM1127RoundedCornerShape0680j_4, textFieldColorsM2708colors0hiis_0, $composer, 806903856, 0, 0, 2096552);
                    boolean zLoginScreen$lambda$349 = LoginScreen$lambda$349($expandedExam$delegate);
                    ComposerKt.sourceInformationMarkerStart($composer, -2066143012, "CC(remember):MainActivity.kt#9igjgp");
                    Object objRememberedValue2 = $composer.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda97
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$434$lambda$428$lambda$427($expandedExam$delegate);
                            }
                        };
                        $composer.updateRememberedValue(obj2);
                        objRememberedValue2 = obj2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ExposedDropdownMenuBox.m2426ExposedDropdownMenuvNxi1II(zLoginScreen$lambda$349, (Function0) objRememberedValue2, null, null, false, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1500827810, true, new Function3() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda98
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj3, Object obj4, Object obj5) {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$434$lambda$433($examOptions, $selectedExam$delegate, $expandedExam$delegate, (ColumnScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, $composer, 54), $composer, 48, (ExposedDropdownMenuBoxScope.$stable << 3) | 6 | (($dirty2 << 3) & 112), 1020);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer.skipToGroupEnd();
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$434$lambda$425$lambda$424(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$434$lambda$426(MutableState $expandedExam$delegate, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C2527@111532L37:MainActivity.kt#t7vdsr");
                if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1227882267, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2527)");
                    }
                    ExposedDropdownMenuDefaults.INSTANCE.TrailingIcon(LoginScreen$lambda$349($expandedExam$delegate), null, $composer, ExposedDropdownMenuDefaults.$stable << 6, 2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer.skipToGroupEnd();
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$434$lambda$428$lambda$427(MutableState $expandedExam$delegate) {
                LoginScreen$lambda$350($expandedExam$delegate, false);
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$434$lambda$433(List $examOptions, final MutableState $selectedExam$delegate, final MutableState $expandedExam$delegate, ColumnScope ExposedDropdownMenu, Composer $composer, int $changed) {
                Composer composer = $composer;
                Intrinsics.checkNotNullParameter(ExposedDropdownMenu, "$this$ExposedDropdownMenu");
                ComposerKt.sourceInformation(composer, "C*2545@112626L16,2546@112694L174,2544@112561L345:MainActivity.kt#t7vdsr");
                if (!composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                    $composer.skipToGroupEnd();
                } else {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1500827810, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2543)");
                    }
                    Iterator it = $examOptions.iterator();
                    while (it.hasNext()) {
                        final String str = (String) it.next();
                        ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(149714722, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda62
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$434$lambda$433$lambda$432$lambda$429(str, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        }, composer, 54);
                        ComposerKt.sourceInformationMarkerStart(composer, -625001536, "CC(remember):MainActivity.kt#9igjgp");
                        boolean zChanged = composer.changed(str);
                        Object objRememberedValue = $composer.rememberedValue();
                        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda63
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$434$lambda$433$lambda$432$lambda$431$lambda$430(str, $selectedExam$delegate, $expandedExam$delegate);
                                }
                            };
                            $composer.updateRememberedValue(obj);
                            objRememberedValue = obj;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        AndroidMenu_androidKt.DropdownMenuItem(composableLambdaRememberComposableLambda, (Function0) objRememberedValue, null, null, null, false, null, null, null, composer, 6, TypedValues.PositionType.TYPE_CURVE_FIT);
                        composer = $composer;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$434$lambda$433$lambda$432$lambda$429(String $option, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C2545@112628L12:MainActivity.kt#t7vdsr");
                if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(149714722, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2545)");
                    }
                    TextKt.m3072TextNvy7gAk($option, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer.skipToGroupEnd();
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$434$lambda$433$lambda$432$lambda$431$lambda$430(String $option, MutableState $selectedExam$delegate, MutableState $expandedExam$delegate) {
                $selectedExam$delegate.setValue($option);
                LoginScreen$lambda$350($expandedExam$delegate, false);
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$438$lambda$437$lambda$436$lambda$435(MutableState $school$delegate, String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                $school$delegate.setValue(it);
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$439(MutableState $errorMessage$delegate, AnimatedVisibilityScope AnimatedVisibility, Composer $composer, int $changed) {
                Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
                ComposerKt.sourceInformation($composer, "C2581@114305L10,2582@114373L11,2579@114208L204:MainActivity.kt#t7vdsr");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-90000734, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2579)");
                }
                TextKt.m3072TextNvy7gAk(LoginScreen$lambda$361($errorMessage$delegate), null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131066);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$445$lambda$444$lambda$443(FirebaseAuth $auth, MutableState $email$delegate, final MutableState $errorMessage$delegate, final Context $context) {
                final String cleanEmail = StringsKt.trim((CharSequence) LoginScreen$lambda$325($email$delegate)).toString().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(cleanEmail, "toLowerCase(...)");
                if (!StringsKt.isBlank(cleanEmail)) {
                    Task<Void> taskSendPasswordResetEmail = $auth.sendPasswordResetEmail(cleanEmail);
                    final Function1 function1 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda141
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$445$lambda$444$lambda$443$lambda$440($context, cleanEmail, (Void) obj);
                        }
                    };
                    taskSendPasswordResetEmail.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda142
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public final void onSuccess(Object obj) {
                            function1.invoke(obj);
                        }
                    }).addOnFailureListener(new OnFailureListener() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda143
                        @Override // com.google.android.gms.tasks.OnFailureListener
                        public final void onFailure(Exception exc) {
                            MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$445$lambda$444$lambda$443$lambda$442($errorMessage$delegate, exc);
                        }
                    });
                } else {
                    $errorMessage$delegate.setValue("Please enter your email address above to receive a password reset link.");
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$445$lambda$444$lambda$443$lambda$440(Context $context, String $cleanEmail, Void it) {
                Toast.makeText($context, "Password reset link sent to " + $cleanEmail, 1).show();
                return Unit.INSTANCE;
            }

            static final void LoginScreen$lambda$468$lambda$467$lambda$466$lambda$445$lambda$444$lambda$443$lambda$442(MutableState $errorMessage$delegate, Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                $errorMessage$delegate.setValue("Failed to send reset link: " + e.getLocalizedMessage());
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458(FirebaseAuth $auth, MutableState $email$delegate, MutableState $password$delegate, final MutableState $errorMessage$delegate, final MutableState $isLoading$delegate, MutableState $isSignUp$delegate, final Context $context, final Function2 $onLoginSuccess, final MutableState $username$delegate, final MutableState $dob$delegate, final MutableState $studentClass$delegate, final MutableState $school$delegate, final MutableState $selectedExam$delegate, final MutableState $profileUri$delegate) {
                final String cleanEmail = StringsKt.trim((CharSequence) LoginScreen$lambda$325($email$delegate)).toString().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(cleanEmail, "toLowerCase(...)");
                String cleanPass = StringsKt.trim((CharSequence) LoginScreen$lambda$328($password$delegate)).toString();
                if (!StringsKt.isBlank(cleanEmail) && !StringsKt.isBlank(cleanPass)) {
                    if (cleanPass.length() >= 6) {
                        LoginScreen$lambda$365($isLoading$delegate, true);
                        $errorMessage$delegate.setValue("");
                        if (LoginScreen$lambda$322($isSignUp$delegate)) {
                            Task<AuthResult> taskCreateUserWithEmailAndPassword = $auth.createUserWithEmailAndPassword(cleanEmail, cleanPass);
                            final Function1 function1 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda132
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$446($context, cleanEmail, $onLoginSuccess, $username$delegate, $dob$delegate, $studentClass$delegate, $school$delegate, $selectedExam$delegate, $profileUri$delegate, $isLoading$delegate, $errorMessage$delegate, (AuthResult) obj);
                                }
                            };
                            taskCreateUserWithEmailAndPassword.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda133
                                @Override // com.google.android.gms.tasks.OnSuccessListener
                                public final void onSuccess(Object obj) {
                                    function1.invoke(obj);
                                }
                            }).addOnFailureListener(new OnFailureListener() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda134
                                @Override // com.google.android.gms.tasks.OnFailureListener
                                public final void onFailure(Exception exc) {
                                    MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$448($isLoading$delegate, $errorMessage$delegate, exc);
                                }
                            });
                        } else {
                            Task<AuthResult> taskSignInWithEmailAndPassword = $auth.signInWithEmailAndPassword(cleanEmail, cleanPass);
                            final Function1 function2 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda135
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$455(cleanEmail, $context, $onLoginSuccess, $isLoading$delegate, $errorMessage$delegate, (AuthResult) obj);
                                }
                            };
                            taskSignInWithEmailAndPassword.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda136
                                @Override // com.google.android.gms.tasks.OnSuccessListener
                                public final void onSuccess(Object obj) {
                                    function2.invoke(obj);
                                }
                            }).addOnFailureListener(new OnFailureListener() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda137
                                @Override // com.google.android.gms.tasks.OnFailureListener
                                public final void onFailure(Exception exc) {
                                    MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$457($isLoading$delegate, $errorMessage$delegate, exc);
                                }
                            });
                        }
                        return Unit.INSTANCE;
                    }
                    $errorMessage$delegate.setValue("Password must be at least 6 characters long.");
                    return Unit.INSTANCE;
                }
                $errorMessage$delegate.setValue("Please enter both email and password.");
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$446(Context $context, String $cleanEmail, Function2 $onLoginSuccess, MutableState $username$delegate, MutableState $dob$delegate, MutableState $studentClass$delegate, MutableState $school$delegate, MutableState $selectedExam$delegate, MutableState $profileUri$delegate, MutableState $isLoading$delegate, MutableState $errorMessage$delegate, AuthResult authResult) {
                FirebaseUser user = authResult.getUser();
                if (user != null) {
                    String uid = user.getUid();
                    Intrinsics.checkNotNullExpressionValue(uid, "getUid(...)");
                    try {
                        Intrinsics.checkNotNull(user.sendEmailVerification());
                    } catch (Exception e) {
                    }
                    ProfilePrefs.INSTANCE.saveUserDetails($context, LoginScreen$lambda$334($username$delegate), LoginScreen$lambda$337($dob$delegate), LoginScreen$lambda$346($studentClass$delegate), LoginScreen$lambda$355($school$delegate), LoginScreen$lambda$352($selectedExam$delegate));
                    if (LoginScreen$lambda$358($profileUri$delegate) != null) {
                        ProfilePrefs profilePrefs = ProfilePrefs.INSTANCE;
                        Uri uriLoginScreen$lambda$358 = LoginScreen$lambda$358($profileUri$delegate);
                        Intrinsics.checkNotNull(uriLoginScreen$lambda$358);
                        profilePrefs.saveProfileImage($context, uriLoginScreen$lambda$358);
                    }
                    saveUserToFirestore($context, uid, LoginScreen$lambda$334($username$delegate), $cleanEmail, LoginScreen$lambda$337($dob$delegate), LoginScreen$lambda$346($studentClass$delegate), LoginScreen$lambda$355($school$delegate), LoginScreen$lambda$352($selectedExam$delegate), true);
                    LoginScreen$lambda$365($isLoading$delegate, false);
                    LoginPrefs.INSTANCE.saveLoginState($context, true, false, $cleanEmail);
                    Toast.makeText($context, "Account created successfully! Welcome to Sushiksha Academy.", 1).show();
                    $onLoginSuccess.invoke(false, $cleanEmail);
                } else {
                    LoginScreen$lambda$365($isLoading$delegate, false);
                    $errorMessage$delegate.setValue("Registration failed. Please try again.");
                }
                return Unit.INSTANCE;
            }

            static final void LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$448(MutableState $isLoading$delegate, MutableState $errorMessage$delegate, Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                LoginScreen$lambda$365($isLoading$delegate, false);
                String localizedMessage = e.getLocalizedMessage();
                if (localizedMessage == null) {
                    localizedMessage = "Registration failed. Email may already be registered.";
                }
                $errorMessage$delegate.setValue(localizedMessage);
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$455(final String $cleanEmail, final Context $context, final Function2 $onLoginSuccess, final MutableState $isLoading$delegate, MutableState $errorMessage$delegate, AuthResult authResult) {
                FirebaseUser user = authResult.getUser();
                if (user != null) {
                    final String uid = user.getUid();
                    Intrinsics.checkNotNullExpressionValue(uid, "getUid(...)");
                    Task<GetTokenResult> idToken = user.getIdToken(true);
                    final Function1 function1 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda129
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$455$lambda$452(uid, $cleanEmail, $context, $onLoginSuccess, $isLoading$delegate, (GetTokenResult) obj);
                        }
                    };
                    idToken.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda130
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public final void onSuccess(Object obj) {
                            function1.invoke(obj);
                        }
                    }).addOnFailureListener(new OnFailureListener() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda131
                        @Override // com.google.android.gms.tasks.OnFailureListener
                        public final void onFailure(Exception exc) {
                            MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$455$lambda$454($cleanEmail, $context, $onLoginSuccess, $isLoading$delegate, exc);
                        }
                    });
                } else {
                    LoginScreen$lambda$365($isLoading$delegate, false);
                    $errorMessage$delegate.setValue("Invalid login credentials.");
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$455$lambda$452(String $uid, final String $cleanEmail, final Context $context, final Function2 $onLoginSuccess, final MutableState $isLoading$delegate, GetTokenResult tokenResult) {
                boolean z = true;
                if (!Intrinsics.areEqual(tokenResult.getClaims().get("admin"), (Object) true) && !Intrinsics.areEqual(tokenResult.getClaims().get("admin"), "true")) {
                    z = false;
                }
                final boolean isAdminClaim = z;
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Intrinsics.checkNotNullExpressionValue(db, "getInstance(...)");
                Task<DocumentSnapshot> task = db.collection(Account.USERS).document($uid).get();
                final Function1 function1 = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda156
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$455$lambda$452$lambda$449(isAdminClaim, $cleanEmail, $context, $onLoginSuccess, $isLoading$delegate, (DocumentSnapshot) obj);
                    }
                };
                task.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda157
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public final void onSuccess(Object obj) {
                        function1.invoke(obj);
                    }
                }).addOnFailureListener(new OnFailureListener() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda158
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        MainActivityKt.LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$455$lambda$452$lambda$451(isAdminClaim, $cleanEmail, $context, $onLoginSuccess, $isLoading$delegate, exc);
                    }
                });
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$455$lambda$452$lambda$449(boolean $isAdminClaim, String $cleanEmail, Context $context, Function2 $onLoginSuccess, MutableState $isLoading$delegate, DocumentSnapshot userDoc) {
                boolean isDocAdmin = Intrinsics.areEqual((Object) userDoc.getBoolean("isAdmin"), (Object) true) || Intrinsics.areEqual(userDoc.getString("role"), "admin");
                boolean finalIsAdmin = $isAdminClaim || isDocAdmin || Intrinsics.areEqual($cleanEmail, "sushiksha.konnagar@gmail.com");
                String string = userDoc.getString(HintConstants.AUTOFILL_HINT_USERNAME);
                if (string == null) {
                    string = StringsKt.substringBefore$default($cleanEmail, "@", (String) null, 2, (Object) null);
                }
                String usernameStr = string;
                String string2 = userDoc.getString("dob");
                String dobStr = string2 == null ? "" : string2;
                String string3 = userDoc.getString("classLevel");
                String classStr = string3 == null ? "" : string3;
                String string4 = userDoc.getString("school");
                String schoolStr = string4 == null ? "" : string4;
                String string5 = userDoc.getString("exam");
                String examStr = string5 == null ? "" : string5;
                ProfilePrefs.INSTANCE.saveUserDetails($context, usernameStr, dobStr, classStr, schoolStr, examStr);
                LoginScreen$lambda$365($isLoading$delegate, false);
                LoginPrefs.INSTANCE.saveLoginState($context, true, finalIsAdmin, $cleanEmail);
                $onLoginSuccess.invoke(Boolean.valueOf(finalIsAdmin), $cleanEmail);
                return Unit.INSTANCE;
            }

            static final void LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$455$lambda$452$lambda$451(boolean $isAdminClaim, String $cleanEmail, Context $context, Function2 $onLoginSuccess, MutableState $isLoading$delegate, Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                boolean fallbackAdmin = $isAdminClaim || Intrinsics.areEqual($cleanEmail, "sushiksha.konnagar@gmail.com");
                LoginScreen$lambda$365($isLoading$delegate, false);
                LoginPrefs.INSTANCE.saveLoginState($context, true, fallbackAdmin, $cleanEmail);
                $onLoginSuccess.invoke(Boolean.valueOf(fallbackAdmin), $cleanEmail);
            }

            static final void LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$455$lambda$454(String $cleanEmail, Context $context, Function2 $onLoginSuccess, MutableState $isLoading$delegate, Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LoginScreen$lambda$365($isLoading$delegate, false);
                boolean fallbackAdmin = Intrinsics.areEqual($cleanEmail, "sushiksha.konnagar@gmail.com");
                LoginPrefs.INSTANCE.saveLoginState($context, true, fallbackAdmin, $cleanEmail);
                $onLoginSuccess.invoke(Boolean.valueOf(fallbackAdmin), $cleanEmail);
            }

            static final void LoginScreen$lambda$468$lambda$467$lambda$466$lambda$459$lambda$458$lambda$457(MutableState $isLoading$delegate, MutableState $errorMessage$delegate, Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LoginScreen$lambda$365($isLoading$delegate, false);
                $errorMessage$delegate.setValue("Invalid email or password. Please try again.");
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$462(MutableState $isLoading$delegate, MutableState $isSignUp$delegate, RowScope Button, Composer $composer, int $changed) {
                Function0<ComposeUiNode> function0;
                Composer composer;
                Function0<ComposeUiNode> function1;
                Intrinsics.checkNotNullParameter(Button, "$this$Button");
                ComposerKt.sourceInformation($composer, "C2728@123342L1684:MainActivity.kt#t7vdsr");
                if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                    $composer.skipToGroupEnd();
                } else {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-631868862, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2728)");
                    }
                    Modifier modifierBackground$default = BackgroundKt.background$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Brush.Companion.m5044horizontalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m5087boximpl(ColorKt.Color(4278355143L)), Color.m5087boximpl(ColorKt.Color(4279673674L)), Color.m5087boximpl(ColorKt.Color(4283096704L))}), 0.0f, 0.0f, 0, 14, (Object) null), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16)), 0.0f, 4, null);
                    Alignment center = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierBackground$default);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int i = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function0 = constructor;
                        $composer.createNode(function0);
                    } else {
                        function0 = constructor;
                        $composer.useNode();
                    }
                    Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                    Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    int i2 = (i >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i3 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -1774728283, "C:MainActivity.kt#t7vdsr");
                    if (LoginScreen$lambda$364($isLoading$delegate)) {
                        $composer.startReplaceGroup(-1774744001);
                        ComposerKt.sourceInformation($composer, "2740@123927L79");
                        composer = $composer;
                        ProgressIndicatorKt.m2727CircularProgressIndicator4lLiAd8(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(24)), Color.INSTANCE.m5134getWhite0d7_KjU(), 0.0f, 0L, 0, 0.0f, composer, 54, 60);
                        $composer.endReplaceGroup();
                    } else {
                        $composer.startReplaceGroup(-1774578368);
                        ComposerKt.sourceInformation($composer, "2742@124068L910");
                        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                        composer = $composer;
                        ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                        Modifier modifier = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                        CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier);
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        int i4 = ((((384 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function1 = constructor2;
                            $composer.createNode(function1);
                        } else {
                            function1 = constructor2;
                            $composer.useNode();
                        }
                        Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                        Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                        int i5 = (i4 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        int i6 = ((384 >> 6) & 112) | 6;
                        Composer composer2 = $composer;
                        ComposerKt.sourceInformationMarkerStart(composer2, 23397877, "C2745@124306L10,2743@124154L262:MainActivity.kt#t7vdsr");
                        TextKt.m3072TextNvy7gAk(LoginScreen$lambda$322($isSignUp$delegate) ? "Create Account" : "Login", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getBodyLarge(), Color.INSTANCE.m5134getWhite0d7_KjU(), 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777210, null), composer2, 0, 0, 131070);
                        if (LoginScreen$lambda$322($isSignUp$delegate)) {
                            composer2.startReplaceGroup(-99788498);
                        } else {
                            composer2.startReplaceGroup(23695786);
                            ComposerKt.sourceInformation(composer2, "2748@124502L39,2749@124578L336");
                            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), composer2, 6);
                            IconKt.m2520Iconww6aTOc(ArrowForwardKt.getArrowForward(Icons.AutoMirrored.Filled.INSTANCE), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(20)), Color.INSTANCE.m5134getWhite0d7_KjU(), composer2, 3504, 0);
                            composer2 = composer2;
                        }
                        composer2.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        composer.endReplaceGroup();
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$464$lambda$463(MutableState $isSignUp$delegate, MutableState $errorMessage$delegate) {
                LoginScreen$lambda$323($isSignUp$delegate, !LoginScreen$lambda$322($isSignUp$delegate));
                $errorMessage$delegate.setValue("");
                return Unit.INSTANCE;
            }

            static final Unit LoginScreen$lambda$468$lambda$467$lambda$466$lambda$465(MutableState $isSignUp$delegate, RowScope OutlinedButton, Composer $composer, int $changed) {
                Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
                ComposerKt.sourceInformation($composer, "C2773@125659L302,2779@125982L39,2782@126178L10,2780@126042L241:MainActivity.kt#t7vdsr");
                if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                    $composer.skipToGroupEnd();
                } else {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1484831420, $changed, -1, "com.example.sushikhasacademyapp.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:2773)");
                    }
                    IconKt.m2520Iconww6aTOc(LoginScreen$lambda$322($isSignUp$delegate) ? ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE) : PersonAddKt.getPersonAdd(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(18)), ColorKt.Color(4279522093L), $composer, 3504, 0);
                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
                    TextKt.m3072TextNvy7gAk(LoginScreen$lambda$322($isSignUp$delegate) ? "Back to Login" : "Create Account", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), ColorKt.Color(4279522093L), 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777210, null), $composer, 0, 0, 131070);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                return Unit.INSTANCE;
            }

            public static final void AcademyAppScreenPreview(Composer $composer, final int $changed) {
                Composer $composer2 = $composer.startRestartGroup(-349485478);
                ComposerKt.sourceInformation($composer2, "C(AcademyAppScreenPreview)2793@126436L59:MainActivity.kt#t7vdsr");
                if (!$composer2.shouldExecute($changed != 0, $changed & 1)) {
                    $composer2.skipToGroupEnd();
                } else {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-349485478, $changed, -1, "com.example.sushikhasacademyapp.AcademyAppScreenPreview (MainActivity.kt:2792)");
                    }
                    ThemeKt.SushikhasAcademyAppTheme(false, false, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$510241912$app(), $composer2, 384, 3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda92
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return MainActivityKt.AcademyAppScreenPreview$lambda$470($changed, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }

            public static final void TeacherProfileCardPreview(Composer $composer, final int $changed) {
                Composer $composer2 = $composer.startRestartGroup(-1421474544);
                ComposerKt.sourceInformation($composer2, "C(TeacherProfileCardPreview)2801@126581L521:MainActivity.kt#t7vdsr");
                if (!$composer2.shouldExecute($changed != 0, $changed & 1)) {
                    $composer2.skipToGroupEnd();
                } else {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1421474544, $changed, -1, "com.example.sushikhasacademyapp.TeacherProfileCardPreview (MainActivity.kt:2800)");
                    }
                    ThemeKt.SushikhasAcademyAppTheme(false, false, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$142826414$app(), $composer2, 384, 3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.MainActivityKt$$ExternalSyntheticLambda70
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return MainActivityKt.TeacherProfileCardPreview$lambda$471($changed, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
        }
