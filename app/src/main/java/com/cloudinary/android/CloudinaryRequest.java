package com.cloudinary.android;

import com.cloudinary.Transformation;

/* JADX INFO: loaded from: classes11.dex */
public class CloudinaryRequest {
    private String publicId;
    private ResponsiveUrl responsive;
    private Transformation transformation;

    private CloudinaryRequest(String publicId, Transformation transformation, ResponsiveUrl responsive) {
        this.publicId = publicId;
        this.transformation = transformation;
        this.responsive = responsive;
    }

    public String getPublicId() {
        return this.publicId;
    }

    public Transformation getTransformation() {
        return this.transformation;
    }

    public ResponsiveUrl getResponsive() {
        return this.responsive;
    }

    public static class Builder {
        private final String publicId;
        private ResponsiveUrl responsive;
        private Transformation transformation;

        public Builder(String publicId) {
            this.publicId = publicId;
        }

        public Builder transformation(Transformation transformation) {
            this.transformation = transformation;
            return this;
        }

        public Builder responsive(ResponsiveUrl responsiveUrl) {
            this.responsive = responsiveUrl;
            return this;
        }

        public Builder responsive(ResponsiveUrl.Preset responsivePreset) {
            this.responsive = MediaManager.get().responsiveUrl(responsivePreset);
            return this;
        }

        public CloudinaryRequest build() {
            return new CloudinaryRequest(this.publicId, this.transformation, this.responsive);
        }
    }
}
