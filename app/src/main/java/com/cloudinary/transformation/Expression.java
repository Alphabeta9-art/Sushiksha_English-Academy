package com.cloudinary.transformation;

/* JADX INFO: loaded from: classes11.dex */
public class Expression extends BaseExpression<Expression> {
    private boolean predefined = false;

    public Expression() {
    }

    public Expression(String name) {
        this.expressions.add(name);
    }

    public static Expression variable(String name, Object value) {
        Expression var = new Expression(name);
        var.expressions.add(value.toString());
        return var;
    }

    public static Expression faceCount() {
        return new Expression("fc");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cloudinary.transformation.BaseExpression
    public Expression newInstance() {
        return new Expression();
    }

    public static Expression width() {
        return new Expression("width");
    }

    public static Expression height() {
        return new Expression("height");
    }

    public static Expression initialWidth() {
        return new Expression("initialWidth");
    }

    public static Expression initialHeight() {
        return new Expression("initialHeight");
    }

    public static Expression aspectRatio() {
        return new Expression("aspectRatio");
    }

    public static Expression initialAspectRatio() {
        return new Expression("initialAspectRatio");
    }

    public static Expression pageCount() {
        return new Expression("pageCount");
    }

    public static Expression currentPage() {
        return new Expression("currentPage");
    }

    public static Expression tags() {
        return new Expression("tags");
    }

    public static Expression pageX() {
        return new Expression("pageX");
    }

    public static Expression pageY() {
        return new Expression("pageY");
    }
}
