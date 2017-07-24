import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CurrentTemperatureComponent } from './current-temperature.component';

const routes: Routes = [
    { path: '', redirectTo: '/current', pathMatch: 'full' },
    { path: 'current', component: CurrentTemperatureComponent },
    // { path: 'detail/:id', component: HeroDetailComponent },
    // { path: 'heroes', component: HeroesComponent }
];

@NgModule({
    imports: [ RouterModule.forRoot(routes)],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}