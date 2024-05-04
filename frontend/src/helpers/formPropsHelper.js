export const formPropsHelper = (register, handleSubmit,handleShowForm, errors,successfulAssembly, onSubmitHelper, uniqueProps) => (
  {
  register,
  handleSubmit,
  handleShowForm,
  errors,
  successfulAssembly,
  onSubmitHelper,
  ...uniqueProps,
});


