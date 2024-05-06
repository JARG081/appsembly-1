/* eslint-disable react/prop-types */


export const DashboardCard = ({title,customClass,children}) => {
  return (
    <div className={`${customClass}`}>
      <h1 className="text-3xl font-semibold">
        {title}
      </h1>
        {children}
    </div>
  )
}
