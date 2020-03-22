package com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Model;

public class RcyCommentModel {

    private String txtCommentNmae;
    private String txtComment;
    private int imgPhoto;

    public RcyCommentModel(String txtCommentNmae, String txtComment, int imgPhoto) {
        this.txtCommentNmae = txtCommentNmae;
        this.txtComment = txtComment;
        this.imgPhoto = imgPhoto;
    }

    public String getTxtCommentNmae() {
        return txtCommentNmae;
    }

    public void setTxtCommentNmae(String txtCommentNmae) {
        this.txtCommentNmae = txtCommentNmae;
    }

    public String getTxtComment() {
        return txtComment;
    }

    public void setTxtComment(String txtComment) {
        this.txtComment = txtComment;
    }

    public int getImgPhoto() {
        return imgPhoto;
    }

    public void setImgPhoto(int imgPhoto) {
        this.imgPhoto = imgPhoto;
    }
}
