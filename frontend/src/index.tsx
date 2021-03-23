import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import "bootstrap/dist/css/bootstrap.min.css";
// Import needed to declare derivative type
import "./model/ActionArgument";
import "./model/ActionDefinitionElement";
import "./model/Annotation";
import "./model/AnnotationType";
import "./model/Assignment";
import "./model/Contract";
import "./model/DefinitionRef";
import "./model/Document";
import "./model/Element";
import "./model/EnumDefinitionElement";
import "./model/EnumValue";
import "./model/EnumVariant";
import "./model/Expression";
import "./model/ExtendedPresentationElement";
import "./model/ExtendedRuleInvocationPresentationElement";
import "./model/ExtendedSectionPresentationElement";
import "./model/ExtendPresentationElementsResultDto";
import "./model/FunctionArgument";
import "./model/FunctionArgumentDefinition";
import "./model/FunctionCall";
import "./model/FunctionElement";
import "./model/FunctionRef";
import "./model/FunctionResult";
import "./model/GenericParameter";
import "./model/GenericType";
import "./model/GenericValidator";
import "./model/GroovyTemplate";
import "./model/Id";
import "./model/InterfaceRef";
import "./model/Library";
import "./model/ListTemplate";
import "./model/MetaArgument";
import "./model/MetaPrimitiveValue";
import "./model/MetaRuleValue";
import "./model/MetaValue";
import "./model/NaturalLanguageDocument";
import "./model/NaturalLanguageDocumentObject";
import "./model/NaturalLanguageProvision";
import "./model/NaturalLanguageSection";
import "./model/NumberRangeValidator";
import "./model/OutputMessage";
import "./model/PresentationElement";
import "./model/Project";
import "./model/RegexValidator";
import "./model/Rule";
import "./model/RuleInterface";
import "./model/RuleInvocation";
import "./model/RuleInvocationPresentationElement";
import "./model/SectionPresentationElement";
import "./model/StateElement";
import "./model/Statement";
import "./model/StateVariableRef";
import "./model/StaticTemplate";
import "./model/Template";
import "./model/TemplateType";
import "./model/TextEngineTemplate";
import "./model/Type";
import "./model/ValidationResult";
import "./model/Validator";
import "./model/VariableRef";
import "./model/WrapWithTemplate";
import "./model/Operation";

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
