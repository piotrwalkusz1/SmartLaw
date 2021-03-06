/** @jsxImportSource @emotion/react */
import React, { useState } from "react";
import TextField from "./TextField";
import { PencilSquare } from "react-bootstrap-icons";
import { css } from "@emotion/react";

const Styles = {
  editIcon: css`
    visibility: hidden;

    div:hover > & {
      visibility: visible;
    }

    &:hover {
      color: red;
    }
  `,
};

interface EditableTextFieldProps {
  value: string;
  onChange: (value: string) => void;
}

const EditableTextField = ({ value, onChange }: EditableTextFieldProps) => {
  const [editMode, setEditMode] = useState(false);

  if (editMode) {
    return <TextField value={value} onChange={onChange} onBlur={() => setEditMode(false)} />;
  } else {
    return (
      <div>
        <span>{value}</span>
        <span
          css={Styles.editIcon}
          onClick={(event) => {
            setEditMode(true);
          }}
        >
          <PencilSquare />
        </span>
      </div>
    );
  }
};

export default EditableTextField;
