/*
 * Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package javafx.scene.control;

/**
Builder class for javafx.scene.control.MultipleSelectionModel
@see javafx.scene.control.MultipleSelectionModel
@deprecated This class is deprecated and will be removed in the next version
* @since JavaFX 2.0
*/
@javax.annotation.Generated("Generated by javafx.builder.processor.BuilderProcessor")
@Deprecated
public abstract class MultipleSelectionModelBuilder<T, B extends javafx.scene.control.MultipleSelectionModelBuilder<T, B>> {
    protected MultipleSelectionModelBuilder() {
    }


    private int __set;
    public void applyTo(javafx.scene.control.MultipleSelectionModel<T> x) {
        int set = __set;
        if ((set & (1 << 0)) != 0) x.getSelectedIndices().addAll(this.selectedIndices);
        if ((set & (1 << 1)) != 0) x.getSelectedItems().addAll(this.selectedItems);
        if ((set & (1 << 2)) != 0) x.setSelectionMode(this.selectionMode);
    }

    private java.util.Collection<? extends java.lang.Integer> selectedIndices;
    /**
    Add the given items to the List of items in the {@link javafx.scene.control.MultipleSelectionModel#getSelectedIndices() selectedIndices} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B selectedIndices(java.util.Collection<? extends java.lang.Integer> x) {
        this.selectedIndices = x;
        __set |= 1 << 0;
        return (B) this;
    }

    /**
    Add the given items to the List of items in the {@link javafx.scene.control.MultipleSelectionModel#getSelectedIndices() selectedIndices} property for the instance constructed by this builder.
    */
    public B selectedIndices(java.lang.Integer... x) {
        return selectedIndices(java.util.Arrays.asList(x));
    }

    private java.util.Collection<? extends T> selectedItems;
    /**
    Add the given items to the List of items in the {@link javafx.scene.control.MultipleSelectionModel#getSelectedItems() selectedItems} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B selectedItems(java.util.Collection<? extends T> x) {
        this.selectedItems = x;
        __set |= 1 << 1;
        return (B) this;
    }

    /**
    Add the given items to the List of items in the {@link javafx.scene.control.MultipleSelectionModel#getSelectedItems() selectedItems} property for the instance constructed by this builder.
    */
    public B selectedItems(T... x) {
        return selectedItems(java.util.Arrays.asList(x));
    }

    private javafx.scene.control.SelectionMode selectionMode;
    /**
    Set the value of the {@link javafx.scene.control.MultipleSelectionModel#getSelectionMode() selectionMode} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B selectionMode(javafx.scene.control.SelectionMode x) {
        this.selectionMode = x;
        __set |= 1 << 2;
        return (B) this;
    }

}
